package com.example;

public class SycTest {

	private StringBuffer sb = new StringBuffer();
	public static void main(String[] args) throws InterruptedException {

		SycTest st = new SycTest();
		for(;;){
			Thread.sleep(1);
			st.get();
		}
	}

	public void get() {
			sb.append("abc");
			sb.append("dd");
	}

}
