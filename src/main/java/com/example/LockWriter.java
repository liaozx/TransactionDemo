package com.example;

public class LockWriter implements Runnable {

	private StringFactory sf;

	public void init(StringFactory sf) {
		this.sf = sf;
	}

	public static void main(String[] args) {

	}

	@Override
	public void run() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sf.set();
	}

}
