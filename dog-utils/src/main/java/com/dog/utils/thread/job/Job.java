package com.dog.utils.thread.job;

/**
 * @Description: 工作线程基类
 * @version: v1.0.0
 * @author:
 * @date: Jul 1, 2012 3:33:55 PM 
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * Jul 1, 2012               v1.0.0               修改原因
 */
public abstract class Job implements Runnable{
	boolean iscycle;//是否周期执行
	int cycleWaitMillis=100;//周期执行时间
	boolean isStop = false;
	JobContext context = new JobContext();
	public Job(){		
	}
	
	public Job(boolean iscycle,int cycleWaitMillis){
		this.iscycle=iscycle;
		this.cycleWaitMillis=cycleWaitMillis;
	}
	
	public int getCycleWaitMillis() {
		return cycleWaitMillis;
	}

	public void setCycleWaitMillis(int cycleWaitMillis) {
		this.cycleWaitMillis = cycleWaitMillis;
	}

	public boolean isIscycle() {
		return iscycle;
	}

	public void setIscycle(boolean iscycle) {
		this.iscycle = iscycle;
	}

	public void run(){
		if(iscycle){
			while(true){
				if(this.isStop){
					break;
				}
				
				doThing(context);

				if(cycleWaitMillis>0){
					try{
						Thread.sleep(cycleWaitMillis);	
					}catch(Exception e){}
				}
			}
		}else{
			doThing(context);			
		}
	}
	
	public abstract void doThing(JobContext context);
	
	/**   
	 * @Description: 终止该线程
	 *
	 * @return
	 * @return：返回结果描述
	 * @throws：异常描述
	 *
	 * Modification History:
	 *          
	 */
	public void stop(){
		this.isStop = true;
	}


	public void setContext(JobContext context) {
		this.context = context;
	}
}
