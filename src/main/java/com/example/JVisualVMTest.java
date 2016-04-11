package com.example;

import java.util.ArrayList;
import java.util.List;

public class JVisualVMTest {

	public static void main(String[] args) throws InterruptedException {

		List<Person> persons = new ArrayList<Person>();
		
		for(int i=0;i<1000000;i++){
			persons.add(new Person("name"+i,"address"+i));
			//Thread.sleep(1);
			//show(persons.get(i));
		}
		System.out.println("over");
	}
	
	public static void show(Object obj){
		System.out.println(obj);
	}

}
