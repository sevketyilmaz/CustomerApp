package com.taximobile.zcustomerapp.model;

import java.sql.Timestamp;

public class Customer {    
    private int _customerId;
    private String _customerName;
    private String _email;
    private String _gsm;
    private double _lat;
    private double _lng;
    private String _password;
    private Timestamp _lastUpdate;
    
    //constructor
    public Customer(){
    	setLastUpdate(System.currentTimeMillis());
    }	
    public Customer(int customerId, double lat, double lng){
		this();
		setCustomerId(customerId);
		setLat(lat);
		setLng(lng);
	}    
    public Customer(int customerId, String customerName, String password){
    	this();
    	setCustomerId(customerId);
    	setCustomerName(customerName);
    	setPassword(password);
    }
    public Customer(int customerId, String customerName, 
    				String email, String gsm, double lat,
    				double lng, String password, String lastUpdate)
    {
    	this(customerId, customerName, password);
    	setEmail(email);
    	setCellPhone(gsm);
    	setLat(lat);
    	setLng(lng);
    	setLastUpdate(lastUpdate);
    }
    public Customer(int customerId, String customerName, 
			String email, String gsm, double lat,
			double lng, String password)
	{
		this(customerId, customerName, password);
		setEmail(email);
		setCellPhone(gsm);
		setLat(lat);
		setLng(lng);
	}

	public int getCustomerId() {
		return _customerId;
	}

	public void setCustomerId(int customerId) {
		this._customerId = customerId;
	}

	public String getCustomerName() {
		return _customerName;
	}

	public void setCustomerName(String customerName) {
		this._customerName = customerName;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		this._email = email;
	}

	public String getCellPhone() {
		return _gsm;
	}

	public void setCellPhone(String gsm) {
		this._gsm = gsm;
	}

	public double getLat() {
		return _lat;
	}

	public void setLat(double lat) {
		this._lat = lat;
	}

	public double getLng() {
		return _lng;
	}

	public void setLng(double lng) {
		this._lng = lng;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		this._password = password;
	}
    
    public Timestamp getLastUpdate(){
    	return _lastUpdate;
    }
    
    public void setLastUpdate(String lastUpdate){
    	String s = lastUpdate.replace('T', ' ');
    	_lastUpdate = Timestamp.valueOf(s);
    }
    
    public void setLastUpdate(Long lastUpdate){
    	_lastUpdate = new Timestamp(lastUpdate);
    }
}
