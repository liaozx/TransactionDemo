package com.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StackOverTest {

	int i=0;
	public static void main(String[] args) {

		
		
		StackOverTest sot = new StackOverTest();
		try {
			sot.doSome();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
//		
//		for(int i=1;i<0;i++){
//			System.out.println(i);
//			
//		}
	}
	
	
	public void doSome() throws InterruptedException{
		List<String> list = new ArrayList<String>();
		while(true){
			list.add(new String(new Date().getSeconds()+""));
			Thread.sleep(5);
		}
	}

}
