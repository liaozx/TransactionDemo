package com.example;

import java.util.concurrent.atomic.AtomicInteger;

public class DefaultMyLock implements MyLock {

	private AtomicInteger writeLockVal = new AtomicInteger(0);
	private AtomicInteger readLockVal = new AtomicInteger(0);

	private Lock writeLock = null;

	private Lock readLock = null;

	public DefaultMyLock() {
		init();
	}

	private void init() {

		writeLock = new Lock() {

			@Override
			public void lock() {
				for (;;) {
					if (readLockVal.get() != 0) {
						continue;
					}
					int write = writeLockVal.get();
					if (write == 0) {
						if (writeLockVal.compareAndSet(0, 1))
							return;
					}
				}

			}

			@Override
			public void unLock() {
				writeLockVal.set(0);
//				for (;;) {
//					int write = writeLockVal.get();
//					if (write == 1) {
//						if (writeLockVal.compareAndSet(1, 0))
//							return;
//					}
//				}
//				
//				
			}

		};

		readLock = new Lock() {

			@Override
			public void lock() {
				for (;;) {
					if (writeLockVal.get() == 1) {
						continue;
					}
					int old = readLockVal.get();
					int next = old + 1;
					if (readLockVal.compareAndSet(old, next))
						return;

				}

			}

			@Override
			public void unLock() {
				for (;;) {
					int old = readLockVal.get();
					int next = old - 1;
					if (readLockVal.compareAndSet(old, next))
						return;

				}

			}

		};

	}

	@Override
	public Lock getWriteLock() {
		return writeLock;
	}

	@Override
	public Lock getReadLock() {
		return readLock;
	}

}
