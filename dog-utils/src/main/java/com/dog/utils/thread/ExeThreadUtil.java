package com.dog.utils.thread;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


import com.dog.utils.common.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.dog.utils.thread.job.CooperateJob;
import com.dog.utils.thread.job.CronExpressionJob;
import com.dog.utils.thread.job.Job;
import com.dog.utils.thread.job.MainAssistantInfo;
import com.dog.utils.thread.job.ScheduledJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 执行线程和工作任务调度类
 * @version: v1.0.0
 * @author: anller
 * @date: Jul 1, 2012 4:04:17 PM 
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * Jul 1, 2012     anller           v1.0.0               修改原因
 */
public class ExeThreadUtil {
	private static Logger log = LoggerFactory.getLogger(ExeThreadUtil.class);

	/**   
	 * @Description: 用threadcount个线程跑job[]个job，跑完就结束，线程资源释放
	 *
	 * @param jobs 任务数组
	 * @param threadcount 线程数
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * Modification History:
	 *          
	 */
	public static void doFixedThreadJob(Job jobs[], int threadcount) {
		try {
			ExecutorService exec = Executors.newFixedThreadPool(threadcount);
			for (Job job : jobs) {
				exec.execute(job);
			}
			exec.shutdown();
		} catch (Exception e) {
			log.equals(e);
		}

	}

	/**   
	 * @Description: 用threadcount个线程跑预定的job，可以循环跑，指定间隔时间，执行次数
	 *
	 * @param jobs 任务数组
	 * @param threadcount 线程数
	 * @param shutdownInterval 运行时长(过shutdownInterval线程结束),0表示不间断
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * Modification History:
	 *          
	 */
	public static void doScheduledThreadJob(ScheduledJob jobs[], int threadcount, long shutdownInterval) {
		try {
			final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(threadcount);
			for (ScheduledJob job : jobs) {

				if (job.getStartInterval() >= 0 && job.getExeInterval() > 0 && null != job.getTimeUnit()) {
					if (job.isDelay()) {
						long start = getRealStartInterval(job.getStartInterval(), job.getTimeUnit());
						ScheduledFuture beeperHandle = scheduler
								.scheduleWithFixedDelay(job, start, job.getExeInterval(), job.getTimeUnit());
						if (job.getExeTimes() > 0) {
							job.setScheduledFuture(beeperHandle);
						}
					} else {
						long start = getRealStartInterval(job.getStartInterval(), job.getTimeUnit());
						ScheduledFuture beeperHandle = scheduler.scheduleAtFixedRate(job, start, job.getExeInterval(), job.getTimeUnit());
						if (job.getExeTimes() > 0) {
							job.setScheduledFuture(beeperHandle);
						}
					}
				}
			}

			if (shutdownInterval > 0) {
				scheduler.schedule(new Runnable() {
					public void run() {
						scheduler.shutdown();
					}
				}, shutdownInterval, TimeUnit.SECONDS);
			}
		} catch (Exception e) {
			log.equals(e);
		}

	}

	/**   
	 * @Description: 根据CronExpression表达式来计算执行的时间,需要quartz-all*.jar包
	 *
	 * @param job 工作任务
	 * @throws Exception
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * Modification History:
	 *          
	 */
	public static void doCronExpressionThreadJob(CronExpressionJob job) throws Exception {
		if (job.getJobType() <= 0 && job.getJobType() >= 4) {
			throw new Exception("error:not support the jobtype.");
		}
		if (job.getJobType() != CronExpressionJob.JOB_TYPE_IMMEDIATELY 
				&& StringUtils.isBlank(job.getCronExpression())) {
			throw new Exception("error:expression is null.");
		}
		if (StringUtils.isBlank(job.getJobName())) {
			throw new Exception("error:jobName is null.");
		}
		if (StringUtils.isBlank(job.getJobGroup())) {
			throw new Exception("error:jobGroup is null.");
		}
		JobDetail exejob = JobBuilder.newJob(job.getClass())
                .withIdentity(job.getJobName(), job.getJobGroup())
                .build();
		//JobDetail exejob = new JobDetail(job.getJobName(), job.getJobGroup(), job.getClass());
		exejob.getJobDataMap().put(CronExpressionJob.JOB_PARAMETERS, job.getContext());

		Trigger trigger = null;
		if (job.getJobType() == CronExpressionJob.JOB_TYPE_CYCLE) {//循环任务    	
			trigger = TriggerBuilder.newTrigger()
					.withIdentity(job.getJobName(), job.getJobGroup())
					.withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()))
					.build();
			//trigger = new CronTrigger(job.getJobName(), job.getJobGroup(), job.getCronExpression());
		} else if (job.getJobType() == CronExpressionJob.JOB_TYPE_FIXED_TIME) {//固定时间执行任务
			DateFormat dateformat = new SimpleDateFormat(DateUtils.DATE_FORMAT_A_YYYYMMDDHHMMSS);
			//trigger = new SimpleTrigger(job.getJobName(), job.getJobGroup(), dateformat.parse(job
			//		.getCronExpression()), null, 0, 0);
			trigger = TriggerBuilder.newTrigger()
					.withIdentity(job.getJobName(), job.getJobGroup())
					.startAt(dateformat.parse(job.getCronExpression()))
					.build();
		} else if (job.getJobType() == CronExpressionJob.JOB_TYPE_IMMEDIATELY) {//立即执行任务
			//trigger = new SimpleTrigger(job.getJobName(), job.getJobGroup(), new Date(), null, 0, 0);
			trigger = TriggerBuilder.newTrigger()
					.withIdentity(job.getJobName(), job.getJobGroup())
					.startAt(new Date())
					.build();
		}

		SchedulerFactory objSchedulerFactory = new StdSchedulerFactory();
		Scheduler objScheduler = objSchedulerFactory.getScheduler();
		job.setObjScheduler(objScheduler);
		objScheduler.scheduleJob(exejob, trigger);
		objScheduler.start();

	}

	/**   
	 * @Description: 多线程协同工作，ArrayList<CooperateJob>[]是多个协同的工作列表，每个ArrayList中可以有多个CooperateJob工作,注意顺序
	 *               系统会分配ArrayList<CooperateJob>[]一样多的线程来分别执行ArrayList工作列表，如果在一个ArrayList中的某个CooperateJob工作设置了isWait=true则线程停止执行，等待其他List中的CooperateJob也执行到isWait=true，只有每个List中都到isWait=true的CooperateJob时才会继续执行下面的CooperateJob工作。
	 *               每一个ArrayList中CooperateJob(true)的数目必须相同，且最后一个必须是true
	 *
	 * @param workLists 工作任务
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * Modification History:
	 *          
	 */
	public static void doCooperateThreadJob(ArrayList<CooperateJob>[] workLists) {

		class Cooperate implements Runnable {
			private CyclicBarrier barrier;

			private ArrayList<CooperateJob> workList;

			public Cooperate(CyclicBarrier barrier, ArrayList<CooperateJob> workList) {
				this.barrier = barrier;
				this.workList = workList;
			}

			public void run() {
				try {
					if (null != workList) {
						for (CooperateJob job : workList) {
							job.run();
							if (job.isWait()) {
								barrier.await();
							}
						}
					}
				} catch (InterruptedException e) {
				} catch (BrokenBarrierException e) {
				}
			}
		}
		if (null != workLists) {
			for (ArrayList<CooperateJob> list : workLists) {
				if (!list.get(list.size() - 1).isWait())
					list.get(list.size() - 1).setWait(true);
			}

			CyclicBarrier barrier = new CyclicBarrier(workLists.length);
			ExecutorService exec = Executors.newFixedThreadPool(workLists.length);
			for (ArrayList workList : workLists) {
				exec.submit(new Cooperate(barrier, workList));
			}
			exec.shutdown();
		}
	}

	/**   
	 * @Description: 一个主线程按顺序处理主要工作，多个副线程同时处理副工作，都处理完了，主线程执行收尾工作
	 *
	 * @param info 工作任务
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * Modification History:
	 *          
	 */
	public static void doMainAssistantThreadJob(MainAssistantInfo info) throws InterruptedException, ExecutionException {
		List<Object> li = new ArrayList<Object>();
		if (null != info.getAssistantJobs()) {
			ExecutorService exec = Executors.newFixedThreadPool(info.getAssistantJobs().length);
			for (Job job : info.getAssistantJobs()) {
				li.add(exec.submit(job));
			}
			exec.shutdown();
		}
		info.getMainJob().run();
		for (int i = 0; i < li.size(); i++) {
			((Future) li.get(i)).get();
		}
		if (null != info.getEndJob()) {
			info.getEndJob().run();
		}

	}

	/**   
	 * @Description: 获取任务开始时间
	 *
	 * @param start 开始时间
	 * @param unit 时间类型
	 * @return
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * Modification History:
	 *          
	 */
	private static long getRealStartInterval(long start, TimeUnit unit) {
		if (unit == TimeUnit.SECONDS && start < 5) {
			start += 5;
		}
		if (unit == TimeUnit.MICROSECONDS && start < 5 * (1000 * 1000)) {
			start += (1000 * 1000) * 5;
		}
		if (unit == TimeUnit.MILLISECONDS && start < 5 * 1000) {
			start += (1000) * 5;
		}
		if (unit == TimeUnit.NANOSECONDS && start < (1000 * 1000 * 1000) * 5) {
			start += (1000 * 1000 * 1000) * 5;
		}
		return start;
	}

}
