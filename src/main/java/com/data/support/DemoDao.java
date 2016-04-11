package com.data.support;

public class DemoDao extends DefaultDaoSupport {

	public void doUpdate() throws Exception {
		
//		if(Thread.currentThread().getId()%5!=0){
//			throw new Exception("hand ralse exception!");
//		}
		System.out.println(" do update..");
		String sql ="";
		this.getTransaction().getConnection().executeUpdate("sql");
	}

	public void doDelete() {
		System.out.println(" do delete..");
	}

	public Object getInfo(){
		System.out.println(" get info..");
		return null;
	}
}
