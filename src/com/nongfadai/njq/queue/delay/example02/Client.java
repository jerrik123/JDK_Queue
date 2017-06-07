package com.nongfadai.njq.queue.delay.example02;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Client {

	public static void main(String[] args) throws InterruptedException {
		final DelayQueue<TaskInfo> queue = new DelayQueue<TaskInfo>();
		
		queue.put(new TaskInfo(5));
		
		System.out.println(queue.isEmpty());
		
		System.out.println(queue.take());
		//System.out.println(queue.poll());
		/*TimeUnit.SECONDS.sleep(1);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(!Thread.currentThread().interrupted()){
					try {
						System.out.println("111");
						TaskInfo taskInfo = queue.take();
						taskInfo.run();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();*/
	}
	
	static class TaskInfo implements Delayed{
		private int order;
		
		public TaskInfo(int order) {
			this.order = order;
		}
		
		public void run(){
			System.out.println("taskInfo-" + order);
		}

		@Override
		public int compareTo(Delayed o) {
		    return (int)(getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return 1;
		}
		
	}
}
