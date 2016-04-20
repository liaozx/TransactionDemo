package com.example.lock;

public class LockWriter implements Runnable {

	private StringFactory sf;

	public void init(StringFactory sf) {
		this.sf = sf;
	}

	public static void main(String[] args) {

	}

	@Override
	public void run() {
		sf.set();
	}

}
