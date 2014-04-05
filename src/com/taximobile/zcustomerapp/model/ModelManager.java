package com.taximobile.zcustomerapp.model;

import android.content.Context;

/*
 * Singleton
 * */
public class ModelManager {
	private static final String TAG = "ModelManager";
	
	private static ModelManager _modelManager;
	private Context _appContext;
	
	private Customer _customer;
	
	private ModelManager(Context appContext){
		_appContext = appContext.getApplicationContext();
		//create customer
		_customer = createInitialCustomer();
	}

	public static ModelManager Get(Context appContext){
		if(_modelManager == null)
			_modelManager = new ModelManager(appContext);
		
		return _modelManager;
	}
	
	//getters
	public Customer getCustomer(){
		return _customer;
	}
	
	private Customer createInitialCustomer() {
		Customer c = new Customer(0, "Test", "test@tm.com", "0555123456",
									26.1234, 36.1234, "test", "2014-01-01T00:00:00");
		return c;
	}
	
}
