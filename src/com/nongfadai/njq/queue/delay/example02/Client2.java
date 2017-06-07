package com.nongfadai.njq.queue.delay.example02;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Client2 {
	public static void main(String[] args) throws InterruptedException {
		DelayQueue<Message> delayQueue = new DelayQueue<>();
		for (int i = 1; i < 11; i++) {
			Message message = new Message(i + "", System.currentTimeMillis() + i * 1000);
			delayQueue.add(message);
		}

		while (!delayQueue.isEmpty()) {
			Message message = delayQueue.take();// 此处会阻塞
			System.out.println(message.getId() + "消息发送:");
		}

	}
}

class Message implements Delayed {
	private String id;
	private long beginTime;// 开始时间

	public Message(String id, long insertTime) {
		this.id = id;
		this.beginTime = insertTime;
	}

	// 获取失效时间
	@Override
	public long getDelay(TimeUnit unit) {
		// 获取失效时间
		return this.beginTime + 3000 - System.currentTimeMillis();
	}

	@Override
	public int compareTo(Delayed o) {
		// 比较 1是放入队尾 -1是放入队头
		Message that = (Message) o;
		if (this.beginTime > that.beginTime) {
			return 1;
		} else if (this.beginTime == that.beginTime) {
			return 0;
		} else {
			return -1;
		}
	}

	public String getId() {
		return id;
	}
}
