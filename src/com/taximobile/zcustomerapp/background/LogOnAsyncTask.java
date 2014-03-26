package com.taximobile.zcustomerapp.background;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.taximobile.zcustomerapp.model.*;


public class LogOnAsyncTask extends AsyncTask<LoginModel, String, Customer>{	
	private static final String TAG = "LogOnAsyncTask";
	private LoginModel _loginModel;
	private Context _context;// Activity context
	private ProgressDialog _progressDialog;
	
	public interface ILogOnReadyListener{
		public void LogOnReady(Customer customer);
	}
	private ILogOnReadyListener _listener;
	
	//constructor
	public LogOnAsyncTask(Context context, Fragment fragment) {
		super();
		_context = context;
		_listener = (ILogOnReadyListener) fragment;
	}
	
	@Override
	protected void onPreExecute() {
		_progressDialog = ProgressDialog.show(_context, "Please Wait", "Process in progress", true, true);

	}
	
	@Override
	protected Customer doInBackground(LoginModel... loginModel) {
		_loginModel = loginModel[0];
		publishProgress("Loging In to the system");		
		
		Customer c = null;
		try {
			c = NetworkManager.LogOnCustomer(_loginModel);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Log.d(TAG, "customer"+ c.toString());
		return c;
	}
	
	@Override
	protected void onPostExecute(Customer customer){
		super.onPostExecute(customer);
		_progressDialog.cancel();
		_listener.LogOnReady(customer);		
	}

	@Override
	protected void onProgressUpdate(String... progress) {
		_progressDialog.setMessage(progress[0]);
	}

}
