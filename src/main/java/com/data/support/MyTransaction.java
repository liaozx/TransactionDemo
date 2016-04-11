package com.data.support;

public interface MyTransaction {

	void begin();

	void commit();

	void rollback();
	
	MyConnection getConnection();
	
	void close();

}
