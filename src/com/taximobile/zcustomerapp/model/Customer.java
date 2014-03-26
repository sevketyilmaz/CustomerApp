package com.taximobile.zcustomerapp.model;

public class Customer {    
    private int _customerId;
    private String _customerName;
    private String _email;
    private String _cellPhone;
    private String _picture;
    private double _lat;
    private double _lng;
    private String _password;
    
    //constructor
    public Customer(){}
    
    public Customer(int customerId, String customerName, String password){
    	setCustomerId(customerId);
    	setCustomerName(customerName);
    	setPassword(password);
    }
    public Customer(int customerId, String customerName, 
    				String email, String cellPhone, String picture,
    				double lat, double lng, String password)
    {
    	this(customerId, customerName, password);
    	setEmail(email);
    	setCellPhone(cellPhone);
    	setPicture(picture);
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
		return _cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this._cellPhone = cellPhone;
	}

	public String getPicture() {
		return _picture;
	}

	public void setPicture(String picture) {
		this._picture = picture;
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
    
    
}
