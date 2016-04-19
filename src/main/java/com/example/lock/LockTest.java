package com.example;

public class LockTest {

	public static void main(String[] args) {
		StringFactory sf = new StringFactory();

		
		for (int i = 0; i < 10000; i++) {
			LockWriter ｗriter = new LockWriter();
			ｗriter.init(sf);
			new Thread(ｗriter).start();
		}
		
		for (int i = 0; i < 10000; i++) {
			LockReader reader = new LockReader();
			reader.init(sf);
			new Thread(reader).start();
		}

		
	

	}

}
