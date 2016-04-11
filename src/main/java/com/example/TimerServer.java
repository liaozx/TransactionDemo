package com.example;

public class TimerServer {

	public static void main(String[] args) {
		
		MultplexerTimerServer server = new MultplexerTimerServer(8888);
		new Thread(server).start();
	}
}
