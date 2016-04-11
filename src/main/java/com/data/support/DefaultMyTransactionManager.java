package com.data.support;

import java.lang.reflect.Method;
import java.util.HashMap;

public class DefaultMyTransactionManager implements MyTransactionManager {

	private static final ThreadLocal<MyTransaction> localTransaction = new ThreadLocal<MyTransaction>();

	private static final HashMap<Method, TransactionDefine> transactionDefineMap = new HashMap<Method, TransactionDefine>();

	@Override
	public void init() {

		try {
			initTransactionDefineMap();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	private void initTransactionDefineMap() throws CloneNotSupportedException {
		TransactionDefine updateTransactionDefine = new TransactionDefine();
		updateTransactionDefine.setReadOnly(false);
		updateTransactionDefine.setTransactionIsolation(TransactionDefine.TRANSACTION_ISOLATION_READ_COMMITED);
		updateTransactionDefine.setTransactionPropagation(TransactionDefine.TRANSACTION_PROPAGATION_REQUERD_NEW);
		
		TransactionDefine readTransactionDefine = (TransactionDefine) updateTransactionDefine.clone();
		readTransactionDefine.setReadOnly(true);
		readTransactionDefine.setTransactionIsolation(TransactionDefine.TRANSACTION_ISOLATION_READ_COMMITED);
		readTransactionDefine.setTransactionPropagation(TransactionDefine.TRANSACTION_PROPAGATION_REQUERD);

		Method[] serviceMethods = ThreadLocalDemoService.class.getMethods();
		for (int i = 0; i < serviceMethods.length; i++) {
			if(serviceMethods[i].getName().startsWith("get")||serviceMethods[i].getName().startsWith("query")||serviceMethods[i].getName().startsWith("search")){
				transactionDefineMap.put(serviceMethods[i], readTransactionDefine);
			}else{
				transactionDefineMap.put(serviceMethods[i], updateTransactionDefine);
			}
		}
	}

	@Override
	public MyTransaction getTransaction() {
		MyTransaction transaction = localTransaction.get();
		if (null == transaction) {
			transaction = new DefaultMyTransaction();
			localTransaction.set(transaction);
			System.out.println("new transaction:" + transaction);
			return transaction;
		}
		System.out.println("old transaction:" + transaction);
		return transaction;
	}

	@Override
	public void setTransaction(MyTransaction transaction) {
		localTransaction.set(transaction);
	}

	@Override
	public TransactionDefine getTransactionDefineByMethod(Method method) {

		TransactionDefine define = transactionDefineMap.get(method);
		if (null == define) {
			define = TransactionDefine.getDefault();
		}

		return define;
	}

}
