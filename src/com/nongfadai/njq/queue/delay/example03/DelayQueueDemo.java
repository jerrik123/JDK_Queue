package com.nongfadai.njq.queue.delay.example03;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueDemo {

	public static void main(String[] args) {
		DelayQueue<DataCache> delayQueue = new DelayQueue<DataCache>();
	}
	
	static class DataCache implements Delayed{

		@Override
		public int compareTo(Delayed o) {
			return 0;
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return 0L;
		}
		
	}

}
