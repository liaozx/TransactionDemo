package com.example.lock;

public interface Lock {
	void lock() throws InterruptedException;
	void unLock() throws InterruptedException;
}
