package com.example.lock;

public class LockReader implements Runnable {

	private StringFactory sf;

	public void init(StringFactory sf) {
		this.sf = sf;
	}

	public static void main(String[] args) {

	}

	@Override
	public void run() {
		sf.get();
	}

}
