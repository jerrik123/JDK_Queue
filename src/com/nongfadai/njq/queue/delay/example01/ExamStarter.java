package com.nongfadai.njq.queue.delay.example01;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 考试开始
 * @author Jerrik
 */
public class ExamStarter {
	static final int STUDENT_SIZE = 45;

	public static void main(String[] args) {
		Random r = new Random();
		DelayQueue<Student> stuDelayQueue = new DelayQueue<Student>();
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < STUDENT_SIZE; i++) {
			stuDelayQueue.put(new Student("学生" + (i + 1), 3000 + r.nextInt(9000)));
		}
		stuDelayQueue.put(new Student.EndExam(12000, exec));// 1200为考试结束时间
		exec.execute(new Teacher(stuDelayQueue, exec));
	}
}
