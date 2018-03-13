package com.dog.utils.thread.job;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public abstract class ScheduledJob extends Job{
	public static TimeUnit SECONDS = TimeUnit.SECONDS;//秒
	public static TimeUnit MICROSECONDS = TimeUnit.MICROSECONDS;//一百万分之一秒
	public static TimeUnit MILLISECONDS = TimeUnit.MILLISECONDS;//毫秒
	public static TimeUnit NANOSECONDS = TimeUnit.NANOSECONDS;//十亿分之一秒
	
	private long startInterval;// 多久之后开始起线程，0表示立即启动 
	private long exeInterval;/* 每间隔执行(如等于5，则是过5秒(单位由timeUnit字段决定)) */
	private TimeUnit timeUnit;//时间单位,如，分，秒...等
	private int exeTimes;/*执行次数，0表示不间断 */
	boolean isDelay;/*是否延时*/
	
	public ScheduledJob(){
		super();
	}
	
	/**
	 * 构造器
	 * @param startInterval 多久之后开始起线程，0表示立即启动 
	 * @param exeInterval 每间隔执行(如等于5，则是过5秒(单位由timeUnit字段决定))
	 * @param exeTimes 执行次数，0表示不间断
	 * @param isDelay 是否延时
	 * @param unit 时间单位,如，分，秒...等
	 */
	public ScheduledJob(long startInterval,long exeInterval,int exeTimes,boolean isDelay,TimeUnit unit){
		super();
		this.startInterval = startInterval;
		this.exeInterval =exeInterval;
		this.exeTimes = exeTimes;
		this.isDelay =isDelay;
		this.timeUnit = unit;
	}
	ScheduledFuture scheduledFuture;
	
	public long getExeInterval() {
		return exeInterval;
	}
	public void setExeInterval(long exeInterval) {
		this.exeInterval = exeInterval;
	}
	public long getStartInterval() {
		return startInterval;
	}
	public void setStartInterval(long startInterval) {
		this.startInterval = startInterval;
	}
	public TimeUnit getTimeUnit() {
		return timeUnit;
	}
	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}
	public int getExeTimes() {
		return exeTimes;
	}
	public void setExeTimes(int exeTimes) {
		this.exeTimes = exeTimes;
	}
	public boolean isDelay() {
		return isDelay;
	}
	public void setDelay(boolean isDelay) {
		this.isDelay = isDelay;
	}
	
	public void setScheduledFuture(ScheduledFuture scheduledFuture) {
		this.scheduledFuture = scheduledFuture;
	}
	
	public void doThing(JobContext context){		
		doSomeThing(context);
		if(this.exeTimes>0 && null != this.scheduledFuture){
			this.exeTimes--;
			if(this.exeTimes<=0){
				scheduledFuture.cancel(true);
			}
		}
		if(isStop){
			scheduledFuture.cancel(true);
		}
		
	}
	public abstract void doSomeThing(JobContext context);	
}
