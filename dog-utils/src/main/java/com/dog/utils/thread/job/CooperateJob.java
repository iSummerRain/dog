package com.dog.utils.thread.job;


/**
 * @Description: 协作线程(多线程协作处理同一件事情)
 *
 * @version: v1.0.0
 * @author:
 * @date: Jul 1, 2012 3:39:48 PM 
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * Jul 1, 2012               v1.0.0               修改原因
 */
public abstract class CooperateJob extends Job{
	boolean isWait;

	public CooperateJob(){
		super();
	}
	public CooperateJob(boolean isWait){
		super();
		this.isWait = isWait;
	}
	
	public boolean isWait() {
		return isWait;
	}

	public void setWait(boolean isWait) {
		this.isWait = isWait;
	}
	
	public void doThing(JobContext context){
		doSomeThing(context);
	}
	
	public abstract void doSomeThing(JobContext context);

}
