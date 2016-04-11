package com.data.support;

public class DefaultDaoSupport implements DaoSupport {

	private MyTransactionManager transactionManger;

	@Override
	public MyTransaction getTransaction() {
		return transactionManger.getTransaction();
	}

	public MyTransactionManager getTransactionManger() {
		return transactionManger;
	}

	public void setTransactionManger(MyTransactionManager transactionManger) {
		this.transactionManger = transactionManger;
	}

}
