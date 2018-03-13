package com.dog.utils.thread.job;

/**
 * 
 * @Description: 主副线程集合类
 *
 * @version: v1.0.0
 * @author: anller
 * @date: Jul 1, 2012 3:48:50 PM 
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * Jul 1, 2012     anller           v1.0.0               修改原因
 */
public class MainAssistantInfo {

	AssistantJob mainJob;
	
	AssistantJob[] assistantJobs;
	
	AssistantJob endJob;

	public Job[] getAssistantJobs() {
		return assistantJobs;
	}

	public void setAssistantJobs(AssistantJob[] assistantJobs) {
		this.assistantJobs = assistantJobs;
	}

	public Job getEndJob() {
		return endJob;
	}

	public void setEndJob(AssistantJob endJob) {
		this.endJob = endJob;
	}

	public Job getMainJob() {
		return mainJob;
	}

	public void setMainJob(AssistantJob mainJob) {
		this.mainJob = mainJob;
	}
	
	
}
