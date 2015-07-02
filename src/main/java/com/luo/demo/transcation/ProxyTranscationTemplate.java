package com.luo.demo.transcation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * BankService proxyBankService = (BankService) proxyTranscationTemplate.proxyFor(bankService);
 * @author hui.luo
 *
 */
public class ProxyTranscationTemplate {
	private TranscationManager transcationManager;

	public ProxyTranscationTemplate(TranscationManager transcationManager) {
		super();
		this.transcationManager = transcationManager;
	}
	
	public Object proxyFor(Object obj){
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),
				new TransactionInvocationHandler(transcationManager,obj));
	}
	
	
	class TransactionInvocationHandler implements InvocationHandler{
		private TranscationManager transcationManager;
		private Object obj;
		
		public TransactionInvocationHandler(TranscationManager transcationManager,Object obj) {
			super();
			this.obj = obj;
			this.transcationManager = transcationManager;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Object result = null;
			try{
				transcationManager.start();
				result = method.invoke(obj, args);
				transcationManager.commit();
			}catch(Exception e){
				transcationManager.rollback();
			}finally{
				transcationManager.close();
			}
			return result;
		}
	}
}
