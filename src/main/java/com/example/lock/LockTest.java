package com.example.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LockTest {

	public static void main(String[] args) {
		StringFactory sf = new StringFactory();

		ExecutorService es = Executors.newFixedThreadPool(4);
		
		long begin  = System.currentTimeMillis();
		for (int i = 0; i <30000; i++) {
			LockWriter ｗriter = new LockWriter();
			ｗriter.init(sf);
			
			LockReader reader = new LockReader();
			reader.init(sf);
			es.execute(new Thread(ｗriter));
			es.execute(new Thread(reader));
		}
		es.shutdown();
		try {
			es.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
			long end = System.currentTimeMillis();
			long cost = (end - begin);
			System.out.println("cost:"+cost+"second!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		
		
		
	

	}

}
