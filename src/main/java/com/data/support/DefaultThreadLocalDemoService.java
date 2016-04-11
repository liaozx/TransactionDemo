package com.data.support;

public class DefaultThreadLocalDemoService implements ThreadLocalDemoService {

	private DemoDao demoDao;

	@Override
	public void doBusiness() throws Exception {
		System.out.println("**********thread begin:" + Thread.currentThread().getName());

		// System.out.println("do business..");
		demoDao.doDelete();
		demoDao.doUpdate();
		System.out.println("**********thread end:" + Thread.currentThread().getName());
	}

	public DemoDao getDemoDao() {
		return demoDao;
	}

	public void setDemoDao(DemoDao demoDao) {
		this.demoDao = demoDao;
	}

	@Override
	public void getInfo() throws Exception {
		demoDao.getInfo();
	}

}
