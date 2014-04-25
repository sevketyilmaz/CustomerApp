package com.taximobile.zcustomerapp.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.taximobile.zcustomerapp.R;
import com.taximobile.zcustomerapp.background.LogOnAsyncTask;
import com.taximobile.zcustomerapp.model.Customer;
import com.taximobile.zcustomerapp.model.LoginModel;
import com.taximobile.zcustomerapp.model.ModelManager;

public class LoginFragment extends Fragment implements LogOnAsyncTask.ILogOnReadyListener{
	private static final String TAG = "LoginFragment";
	
	private EditText userNameEditText, passwordEditText;
	private Button loginButton;
	private Customer _customer;
	
	FragmentManager fm;
	InputMethodManager inMgr;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		_customer = ModelManager.Get().getCustomer();
		
		inMgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_login, container, false);
		
		userNameEditText = (EditText) view.findViewById(R.id.userNameEditText);
		passwordEditText = (EditText) view.findViewById(R.id.passwordEditText);
		
		loginButton = (Button) view.findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if ((!userNameEditText.getText().toString().matches("")) && (!passwordEditText.getText().toString().matches(""))) {
					//checking if user is authenticated
					checkPassword(userNameEditText.getText(),
							passwordEditText.getText());
				}			
				userNameEditText.requestFocus();
			}
		});
		
		return view;
	}
	
	//call back method from LogOnAsyncTask
	public void LogOnReady(Customer customer){
		_customer = customer;
		ModelManager.Get().setCustomer(customer);
		if(customer !=null){
			//get the softkeypad disappear
			inMgr.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
			
			fm = getFragmentManager();
			fm.beginTransaction()
				.replace(R.id.fragmentContainer, new MyMapFragment())
				.commit();
		}else{
			userNameEditText.setText("");
			passwordEditText.setText("");
		}
	}
	
	private void checkPassword(Editable uName, Editable pass){
		//TODO check if user name and password correct
		
		LoginModel loginModel = new LoginModel(uName.toString(), pass.toString());
		LogOnAsyncTask task = new LogOnAsyncTask(getActivity(), this);
		task.execute(loginModel);
	}
}
