package com.dog.utils.thread.job;

/**
 * @Description: 主副助理线程
 * @date: Jul 1, 2012 3:39:01 PM 
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * Jul 1, 2012               v1.0.0               修改原因
 */
public abstract class AssistantJob extends Job{

	public AssistantJob(){
		
	}

	public void doThing(JobContext context){
		doSomeThing(context);
	}
	
	public abstract void doSomeThing(JobContext context);
}
