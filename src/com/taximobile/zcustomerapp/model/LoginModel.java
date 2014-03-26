package com.taximobile.zcustomerapp.model;

public class LoginModel {
	private String _userName;
	private String _password;

	//constructors
	public LoginModel(){}
	
	public LoginModel(String userName, String password)
	{
		setUserName(userName);
		setPassword(password);
	}

	//getter setters
	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		_password = password;
	}
		
}
