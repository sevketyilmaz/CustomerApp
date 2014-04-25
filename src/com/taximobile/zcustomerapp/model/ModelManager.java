package com.taximobile.zcustomerapp.model;


/*
 * Singleton
 * */
public class ModelManager {
	private static final String TAG = "ModelManager";
	
	private static ModelManager _modelManager = null;
	
	private Customer _customer;
	
	private ModelManager(){
		//create customer
		//_customer = createInitialCustomer();
		_customer = new Customer();
	}

	public static ModelManager Get(){
		if(_modelManager == null)
			_modelManager = new ModelManager();
		
		return _modelManager;
	}
	
	//getters
	public Customer getCustomer(){
		return _customer;
	}
	
	public void setCustomer(Customer c){
		_customer = c;
	}
	
//	private Customer createInitialCustomer() {
//		Customer c = new Customer(0, "Test", "test@tm.com", "0555123456",
//									26.1234, 36.1234, "test", "2014-01-01T00:00:00");
//		return c;
//	}
	
}
