package com.data.support;

public class TransactionDefine implements Cloneable{

	private int transactionIsolation = TransactionDefine.TRANSACTION_ISOLATION_READ_COMMITED;

	private int transactionPropagation = TransactionDefine.TRANSACTION_PROPAGATION_REQUERD;

	private boolean isReadOnly = false;

	public static int TRANSACTION_ISOLATION_READ_UNCOMMITED = 1;
	public static int TRANSACTION_ISOLATION_READ_COMMITED = 2;
	public static int TRANSACTION_ISOLATION_READ_REPEATED_ABLED = 2;

	public static int TRANSACTION_PROPAGATION_REQUERD = 1;
	public static int TRANSACTION_PROPAGATION_REQUERD_NEW = 2;
	public static int TRANSACTION_PROPAGATION_SUPPORT = 3;

	private static TransactionDefine defaultDefine = null;

	public int getTransactionIsolation() {
		return transactionIsolation;
	}

	public void setTransactionIsolation(int transactionIsolation) {
		this.transactionIsolation = transactionIsolation;
	}

	public int getTransactionPropagation() {
		return transactionPropagation;
	}

	public void setTransactionPropagation(int transactionPropagation) {
		this.transactionPropagation = transactionPropagation;
	}

	public boolean isReadOnly() {
		return isReadOnly;
	}

	public void setReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}

	public static TransactionDefine getDefault() {
		if (null == defaultDefine) {
			defaultDefine = new TransactionDefine();
		}
		return defaultDefine;
	}

	@Override
	public String toString() {
		return "TransactionDefine [transactionIsolation=" + transactionIsolation + ", transactionPropagation="
				+ transactionPropagation + ", isReadOnly=" + isReadOnly + "]";
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	

}
