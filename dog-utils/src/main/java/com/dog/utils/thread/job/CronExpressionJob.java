package com.dog.utils.thread.job;

import java.util.Iterator;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 根据时间正则表达试调度工作
 * @version: v1.0.0
 * @author: anller
 * @date: Jul 1, 2012 3:57:43 PM 
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * Jul 1, 2012     anller           v1.0.0               修改原因
 */
public abstract class CronExpressionJob implements org.quartz.Job{
	private static Logger log= LoggerFactory.getLogger(CronExpressionJob.class);
	public static int JOB_TYPE_FIXED_TIME=1;//固定时间执行一次
	public static int JOB_TYPE_IMMEDIATELY=2;//立即执行
	public static int JOB_TYPE_CYCLE=3;//循环执行	
	public static String JOB_NAME="CRONEXPRESSION_JOB_NAME";//工作名
	public static String JOB_GROUP="CRONEXPRESSION_JOB_GROUP";//工作组
	public static String JOB_PARAMETERS="CRONEXPRESSION_JOB_PARAMETERS";//参数名
	
	private String cronExpression; /* 0 0 0 * * ? * */
	private int jobType;
	private String jobName;
	private String jobGroup;
	private boolean isStop = false;
	private JobContext context = new JobContext();
	Scheduler objScheduler;
			

	public void setObjScheduler(Scheduler objScheduler) {
		this.objScheduler = objScheduler;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	public int getJobType() {
		return jobType;
	}

	public void setJobType(int jobType) {
		this.jobType = jobType;
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
        	//获取正在运行的任务
        	List runningJobs = context.getScheduler().getCurrentlyExecutingJobs();
            //打印正在运行的任务
        	if (log.isDebugEnabled()) {
                for (Iterator iter = runningJobs.iterator(); iter.hasNext();) {
                    JobExecutionContext item = (JobExecutionContext) iter.next();                                        
                        //log.debug("running,task ID:" + item.getJobDetail().getName() +" group :"+item.getJobDetail().getGroup() + ",exe class:" +
                        //        item.getJobDetail().getJobClass().getName());    
                    log.debug("running,task ID:" + item.getJobDetail().getKey().getName() +" group :"+item.getJobDetail().getKey().getGroup() + ",exe class:" +
                            item.getJobDetail().getJobClass().getName());   
                }
            }
        	String name = (String)context.getJobDetail().getJobDataMap().get(JOB_NAME);
        	String group = (String)context.getJobDetail().getJobDataMap().get(JOB_GROUP);
        	//任务是否正在执行        	   
			if (isRunning(name, group, runningJobs)) {
                log.info("job:" + jobName + ",is running");                     
            }else{            	
            	doSomeThing((JobContext)context.getJobDetail().getJobDataMap().get(JOB_PARAMETERS));
            }	
			if(isStop){
				if(null != objScheduler) objScheduler.shutdown(); 
			}
        }
        catch (Throwable ex) {
            log.error("cronexpression task error:", ex);
        }
    }
	
	private static boolean isRunning(String jobName, String groupName, List runningJobs) throws Exception {
        boolean rtn = false;
        for (Iterator iter = runningJobs.iterator(); iter.hasNext();) {
            JobExecutionContext item = (JobExecutionContext) iter.next();
            //if (item.getJobDetail().getName().equalsIgnoreCase(jobName)
            if (item.getJobDetail().getKey().getGroup().equalsIgnoreCase(jobName)
                    &&
                    //item.getJobDetail().getGroup().equalsIgnoreCase(groupName)) {
                    item.getJobDetail().getKey().getGroup().equalsIgnoreCase(groupName)) {
                rtn = true;
                break;
            }
        }
        return rtn;
    }	
	
	public abstract void doSomeThing(JobContext context);
	
	public void stop(){
		this.isStop = true;
	}

	public JobContext getContext() {
		return context;
	}

	public void setContext(JobContext context) {
		this.context = context;
	}
	
}
