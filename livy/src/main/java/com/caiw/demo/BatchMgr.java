package com.caiw.demo;

import org.quartz.JobExecutionContext;

public interface BatchMgr {

	/**
	 * 执行跑批
	 */

	public  void invoke(JobExecutionContext context);

}
