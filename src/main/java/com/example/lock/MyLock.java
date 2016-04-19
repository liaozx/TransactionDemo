package com.example;

public interface MyLock{
	Lock getWriteLock();

	Lock getReadLock();

}
