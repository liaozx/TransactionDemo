package com.data.support.test;

public class ThreadLocalTest {

	public static void main(String[] args) {
		
		DemoDataServer server = new DemoDataServer();
		
		for(int i=0;i<1;i++){
			new Thread(server).start();
		}
	}

}
