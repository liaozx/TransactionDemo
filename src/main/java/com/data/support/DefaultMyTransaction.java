package com.data.support;

public class DefaultMyTransaction implements MyTransaction {

	private MyConnection connection;

	public DefaultMyTransaction() {
		connection = new MyConnection();
	}

	@Override
	public void begin() {
		connection.begin();
		log();
	}

	public void log(){
		System.out.println(this);
	}
	@Override
	public void commit() {
		connection.commit();
		log();
	}

	@Override
	public void rollback() {
		connection.rollback();
		log();
	}

	@Override
	public MyConnection getConnection() {
		if (null == connection) {
			connection = new MyConnection();
		}
		return connection;
	}

	@Override
	public void close() {
		connection.close();
		log();
	}

	@Override
	public String toString() {
		return "DefaultMyTransaction [connection=" + connection + "]";
	}

	
}
