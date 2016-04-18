package com.example;

import java.util.HashMap;

public class MyHashMap<K, V> extends HashMap<K, V> {

	private MyLock lock = new DefaultMyLock();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public V get(Object key) {
		try {
			lock.getReadLock().lock();
			return super.get(key);
		} finally {
			lock.getReadLock().unLock();
		}

	}

	@Override
	public V put(K key, V value) {
		try {
			lock.getWriteLock().lock();
			return super.put(key, value);
		} finally {
			lock.getWriteLock().unLock();
		}
	}

}
