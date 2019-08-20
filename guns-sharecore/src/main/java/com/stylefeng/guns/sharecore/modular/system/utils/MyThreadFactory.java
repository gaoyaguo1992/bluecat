package com.stylefeng.guns.sharecore.modular.system.utils;

import java.util.concurrent.ThreadFactory;
/**
 *进程池..
 */
public class MyThreadFactory implements ThreadFactory {
	private String threadName;
	/* (non-Javadoc)
	 * @see java.util.concurrent.ThreadFactory#newThread(java.lang.Runnable)
	 */
	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r,threadName);
	}

	public MyThreadFactory(String threadName){
		this.threadName = threadName;
	}
}
