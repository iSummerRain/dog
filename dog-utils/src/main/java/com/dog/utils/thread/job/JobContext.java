package com.dog.utils.thread.job;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @Description: 线程工作上下文
 * @version: v1.0.0
 * @author: anller
 * @date: Jul 1, 2012 4:44:08 PM 
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * Jul 1, 2012     anller           v1.0.0               修改原因
 */
public class JobContext implements Serializable{

	private static final long serialVersionUID = -157079839668360545L;
	
	//参数Map
	private HashMap<String,Object> paramMap = new HashMap<String,Object>();
	
	public void setParam(String key,Object value){
		paramMap.put(key, value);
	}
	
	public Object getParam(String key){
		return paramMap.get(key);
	}
}
