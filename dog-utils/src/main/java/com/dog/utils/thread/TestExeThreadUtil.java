package com.dog.utils.thread;

import java.util.ArrayList;

import com.dog.utils.thread.job.CooperateJob;
import com.dog.utils.thread.job.CronExpressionJob;
import com.dog.utils.thread.job.JobContext;
import com.dog.utils.thread.job.ScheduledJob;
import junit.framework.TestCase;

public class TestExeThreadUtil extends TestCase {

	public static void doFixedThreadJob() {
		System.out.println("------------------------testdoFixedThreadJob");
		ScheduledJob[] jobs = new ScheduledJob[] { new ScheduledJob(0, 2, 0, false, ScheduledJob.SECONDS) {
			public void doSomeThing(JobContext context) {
				System.out.println("Interval 2 second");
			}
		}, new ScheduledJob(7, 5, 0, false, ScheduledJob.SECONDS) {
			public void doSomeThing(JobContext context) {
				System.out.println("Interval 5 second");
			}
		}, new ScheduledJob(2, 3, 3, false, ScheduledJob.SECONDS) {
			public void doSomeThing(JobContext context) {
				System.out.println("Interval 3 second 3 times");
			}
		}, new ScheduledJob(2, 1000000, 4, false, ScheduledJob.MICROSECONDS) {
			public void doSomeThing(JobContext context) {
				System.out.println("Interval 1000000 MICROSECONDS 4 times");
			}
		}, new ScheduledJob(0, 10000, 5, false, ScheduledJob.NANOSECONDS) {
			public void doSomeThing(JobContext context) {
				System.out.println("Interval 10000 NANOSECONDS 5 times");
			}
		}, new ScheduledJob(3, 4, 0, true, ScheduledJob.SECONDS) {
			public void doSomeThing(JobContext context) {
				System.out.println("Interval 4 second true");
			}
		} };
		ExeThreadUtil.doFixedThreadJob(jobs, 1);
	}

	public static void testdoScheduledThreadJob() {
		System.out.println("------------------------testdoScheduledThreadJob");
		ScheduledJob[] jobs = new ScheduledJob[] {

				new ScheduledJob(0, 5, 100, false, ScheduledJob.SECONDS) {
					public void doSomeThing(JobContext context) {
						System.out.println(
								"Interval 10000 NANOSECONDS 5 times [thread]" + Thread.currentThread().getName());
					}
				} };
		ExeThreadUtil.doScheduledThreadJob(jobs, 1, 10);
	}
	
	/**
	 * 时间表达式
	 * @throws Exception 
	 */
	public static void doCronExpressionThreadJob1() throws Exception {
		System.out.println("------------------------doCronExpressionThreadJob1");

		CronExpressionJob job = new CronExpressionJob() {
					public void doSomeThing(JobContext context) {
						
						System.out.println(
								"Interval 10000 NANOSECONDS 5 times [thread]" + Thread.currentThread().getName());
					}
				} ;
				job.setJobName("test1");
				job.setJobGroup("testGroup1");
				//job.setObjScheduler(objScheduler);
				job.setJobType(CronExpressionJob.JOB_TYPE_IMMEDIATELY);//周期执行
				//job.setCronExpression(cronExpression);
		
		ExeThreadUtil.doCronExpressionThreadJob(job);
	}

	public static void main8(String[] args) {
		System.out.println("------------------------doCooperateThreadJob");
		ArrayList<CooperateJob>[] workLists = new ArrayList[3];
		workLists[0] = new ArrayList<CooperateJob>();
		workLists[1] = new ArrayList<CooperateJob>();
		workLists[2] = new ArrayList<CooperateJob>();

		workLists[0].add(new CooperateJob(true) {
			public void doSomeThing(JobContext context) {
				System.out.println("group1 do thing 1");
			}

			public boolean isExit() {
				// TODO Auto-generated method stub
				return false;
			}
		});
		workLists[0].add(new CooperateJob() {
			public void doSomeThing(JobContext context) {
				System.out.println("group1 do thing 2 waiting...");
			}

			public boolean isExit() {
				// TODO Auto-generated method stub
				return false;
			}
		});
		workLists[0].add(new CooperateJob() {
			public void doSomeThing(JobContext context) {
				System.out.println("group1 do thing 3 ");
			}
		});

		workLists[1].add(new CooperateJob() {
			public void doSomeThing(JobContext context) {
				System.out.println("group2 do thing 1 waiting...");
			}
		});
		workLists[1].add(new CooperateJob(true) {
			public void doSomeThing(JobContext context) {
				System.out.println("group2 do thing 2 ");
			}
		});
		workLists[1].add(new CooperateJob() {
			public void doSomeThing(JobContext context) {
				System.out.println("group2 do thing 3 ");
			}
		});

		workLists[2].add(new CooperateJob() {
			public void doSomeThing(JobContext context) {
				System.out.println("group3 do thing 1 ");
			}
		});
		workLists[2].add(new CooperateJob(true) {
			public void doSomeThing(JobContext context) {
				System.out.println("group3 do thing 2 ");
			}
		});
		workLists[2].add(new CooperateJob() {
			public void doSomeThing(JobContext context) {
				System.out.println("group3 do thing 3 waiting... ");
			}
		});

		ExeThreadUtil.doCooperateThreadJob(workLists);
	}

	public static void main(String[] args) {
		try {

			//testdoScheduledThreadJob();
			doCronExpressionThreadJob1();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
