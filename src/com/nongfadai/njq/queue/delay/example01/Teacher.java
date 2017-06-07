package com.nongfadai.njq.queue.delay.example01;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;

class Teacher implements Runnable {
	private DelayQueue<Student> stuDelayQueue;
	private ExecutorService exec;

	public Teacher(DelayQueue<Student> students, ExecutorService exec) {
		super();
		this.stuDelayQueue = students;
		this.exec = exec;
	}

	@Override
	public void run() {
		try {
			System.out.println("考试开始……");
			while (!Thread.interrupted()) {
				stuDelayQueue.take().run();
			}
			System.out.println("考试结束……");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
