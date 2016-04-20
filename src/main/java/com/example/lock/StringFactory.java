package com.example.lock;

public class StringFactory {
	private static final int LEN = 9;
	private int[] chars = new int[LEN];
	private MyLock lock = new DefaultMyLock();

	public void get() {
		try {
			lock.getReadLock().lock();
			String str = "";
			for (int i = 0; i < LEN; i++) {
				str += chars[i];
			}
			System.out.println(str + "---");
			System.out.flush();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				lock.getReadLock().unLock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void set() {
		try {
			lock.getWriteLock().lock();
			for (int i = 0; i < LEN; i++) {
				chars[i] = i + 1;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				lock.getWriteLock().unLock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
