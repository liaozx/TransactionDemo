package com.example;

public class TimerClient {

	public static void main(String[] args) {
		
		TimerClientHandle client = new TimerClientHandle(8888);
		new Thread(client).start();
	}
}
