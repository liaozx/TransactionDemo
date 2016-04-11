package com.data.support.test;

import java.util.HashMap;

import com.data.support.DefaultMyTransactionManager;
import com.data.support.DefaultThreadLocalDemoService;
import com.data.support.DemoDao;
import com.data.support.MyTransactionInvocationHandler;
import com.data.support.MyTransactionManager;
import com.data.support.ThreadLocalDemoService;
import com.data.support.TransactionDefine;

public class DemoDataServer implements Runnable {

	private HashMap<String, Object> beanMap = new HashMap<String, Object>();

	public void proc() throws Exception {
		//
		ThreadLocalDemoService service = (ThreadLocalDemoService) findAddStoreBean("demoService",
				DefaultThreadLocalDemoService.class);

		MyTransactionManager transactionManager = (MyTransactionManager) findAddStoreBean("transactionManager",
				DefaultMyTransactionManager.class);

		
		transactionManager.init();
		
		DemoDao demoDao = (DemoDao) findAddStoreBean("demoDao", DemoDao.class);
		demoDao.setTransactionManger(transactionManager);
		((DefaultThreadLocalDemoService) service).setDemoDao(demoDao);

		MyTransactionInvocationHandler invocationHandler = (MyTransactionInvocationHandler) findAddStoreBean(
				"invocationHandler", MyTransactionInvocationHandler.class);

		invocationHandler.setTarget(service);
		invocationHandler.setTransactionManager(transactionManager);

		service = (ThreadLocalDemoService) invocationHandler.getProxy();

		service.doBusiness();
		
		service.getInfo();

	}

	private Object findAddStoreBean(String beanName, Class clazz)
			throws InstantiationException, IllegalAccessException {
		Object bean = beanMap.get(beanName);
		if (null == bean) {
			bean = clazz.newInstance();
			beanMap.put(beanName, bean);
		}
		return bean;
	}

	@Override
	public void run() {

		try {
			proc();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
