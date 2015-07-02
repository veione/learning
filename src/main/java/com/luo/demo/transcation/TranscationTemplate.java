package com.luo.demo.transcation;

import java.sql.SQLException;

public abstract class TranscationTemplate {
	private TranscationManager transcationManager;
	
	public TranscationTemplate(TranscationManager transcationManager) {
		super();
		this.transcationManager = transcationManager;
	}

	public void doJobInTransaction() throws SQLException{
		try{
			transcationManager.start();
			run();
			transcationManager.commit();
		}catch(Exception e){
			transcationManager.rollback();
		}finally{
			transcationManager.close();
		}
	}
	
	public abstract void run();
}
