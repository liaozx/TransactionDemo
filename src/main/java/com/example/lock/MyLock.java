package com.example.lock;

public interface MyLock{
	Lock getWriteLock();

	Lock getReadLock();

}
