package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableTest {

	public static void main2(String[] args) {

		// 这里是把对象序列化到文件
		Person crab = new Person();
		crab.setName("Mr.Crab");
		ObjectOutputStream oo;
		try {
			oo = new ObjectOutputStream(new FileOutputStream("crab_file"));
			oo.writeObject(crab);
			oo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {

		// 这里是把对象序列化到文件
		Person crab = null;
		ObjectInputStream oi;
		try {
			oi = new ObjectInputStream(new FileInputStream("crab_file"));
			crab = (Person) oi.readObject();
			oi.close();
			System.out.println(crab);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
