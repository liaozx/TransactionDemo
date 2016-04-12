package com.example;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileTest implements Runnable {

	private CountDownLatch countDown = new CountDownLatch(1000);

	private volatile AtomicInteger lock = new AtomicInteger(0);
	private volatile int number = 0;

	private ReentrantLock rlock= null;
	
	
	public static void main(String[] args) {

		VolatileTest test = new VolatileTest();
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 200000; i++) {
			new Thread(test).start();
		}

		try {
			test.countDown.await();
		    long end = System.currentTimeMillis();
			System.out.println("number:" + test.number);
			System.out.println("cost:"+(end-begin));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ConcurrentHashMap<String, String> map;
		HashMap<String,String> hmap;
	}

	@Override
	public void run() {
		try {
//			lock();
			synchronized(this){
				number++;
			}

		} finally {
//			unlock();
			countDown.countDown();
		}
	}

	private void lock() {
		for (;;) {
			if (lock.compareAndSet(0, 1))
				break;

		}
	}

	private void unlock() {
		lock.set(0);
	}
}
