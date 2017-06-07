package com.nongfadai.njq.queue.delay.example01;

import java.util.concurrent.TimeUnit;

public class TimeUnitTester {

	public static void main(String[] args) {
		// 将 一分钟转换成秒钟数
		System.out.println(TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES));//60
		
		//将1毫秒转换成纳秒数
		System.out.println(TimeUnit.NANOSECONDS.convert(1, TimeUnit.MILLISECONDS));
		
	}

}
