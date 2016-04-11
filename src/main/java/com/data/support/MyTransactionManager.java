package com.data.support;

import java.lang.reflect.Method;

public interface MyTransactionManager {
	MyTransaction getTransaction();

	void setTransaction(MyTransaction transaction);

	TransactionDefine getTransactionDefineByMethod(Method method);

	void init();
}
