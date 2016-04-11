package com.data.support;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyTransactionInvocationHandler implements InvocationHandler {

	// 目标对象
	private Object target;

	private MyTransactionManager transactionManager = null;

	public MyTransactionInvocationHandler() {
		super();
	}

	public MyTransactionInvocationHandler(ThreadLocalDemoService service) {
		super();
		this.target = service;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		MyTransaction curTransaction = null;
		TransactionDefine transactionDefine = null;
		try {
			if (null == transactionManager) {
				throw new Exception("none transactionManager inited!");
			}
			transactionDefine = transactionManager.getTransactionDefineByMethod(method);
			if (null == transactionDefine) {
				throw new Exception("none transactionDefine inited!");
			}

			System.out.println("invoke by transactionDefine:"+ transactionDefine);
			
			if (transactionDefine
					.getTransactionPropagation() == TransactionDefine.TRANSACTION_PROPAGATION_REQUERD_NEW) {
				curTransaction = transactionManager.getTransaction();
				MyTransaction newTransaction = new DefaultMyTransaction();
				this.transactionManager.setTransaction(newTransaction);
			}

			transactionManager.getTransaction().getConnection()
					.setTransactionIsolation(transactionDefine.getTransactionIsolation());
			transactionManager.getTransaction().getConnection().setReadOnly(transactionDefine.isReadOnly());

			transactionManager.getTransaction().begin();

			System.out.println("begin invoke method:"+target.getClass()+"->"+method.getName());
			// 执行目标对象的方法
			Object result = method.invoke(target, args);

			transactionManager.getTransaction().commit();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			if (null != transactionManager) {
				transactionManager.getTransaction().rollback();
			}
		} finally {
			if (null != transactionManager.getTransaction()) {
				transactionManager.getTransaction().close();
			}
			if (transactionDefine
					.getTransactionPropagation() == TransactionDefine.TRANSACTION_PROPAGATION_REQUERD_NEW) {
				transactionManager.setTransaction(curTransaction);
			}

		}
		return null;
	}

	/**
	 * 获取目标对象的代理对象
	 * 
	 * @return 代理对象
	 */
	public Object getProxy() {
		return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(),
				this);
	}

	public MyTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(MyTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

}
