package com.nongfadai.njq.queue.delay.example01;

import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 模拟考试，时间为120分钟，
 * 学生可以在30分钟后交卷， 
 * 当学生都交完了 或 时间到者考试结束
 */
class Student implements Runnable, Delayed {
	private String name;
	private long submitTime;// 交卷时间
	private long workTime;// 考试时间

	public Student() {
	}

	public Student(String name, long submitTime) {
		super();
		this.name = name;
		workTime = submitTime;
		// 都毫秒数 转换成纳秒
		this.submitTime = TimeUnit.NANOSECONDS.convert(submitTime, TimeUnit.MILLISECONDS) + System.nanoTime();
	}

	@Override
	public void run() {
		System.out.println(name + " 交卷,用时" + workTime / 100 + "分钟");
	}

	@Override
	public long getDelay(TimeUnit unit) {//延迟队列传入的是纳秒=DelayQueue.getDelay(TimeUnit.NANOSECONDS)
		return unit.convert(submitTime - System.nanoTime(), unit.NANOSECONDS);
	}

	@Override
	public int compareTo(Delayed o) {
		Student that = (Student) o;
		return submitTime > that.submitTime ? 1 : (submitTime < that.submitTime ? -1 : 0);
	}

	public static class EndExam extends Student {
		private ExecutorService exec;

		public EndExam(int submitTime, ExecutorService exec) {
			super(null, submitTime);
			this.exec = exec;
		}

		@Override
		public void run() {
			exec.shutdownNow();
		}
	}
}
