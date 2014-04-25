package com.taximobile.zcustomerapp.background;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.text.method.DateTimeKeyListener;
import android.util.Log;
import com.taximobile.zcustomerapp.MainActivity;
import com.taximobile.zcustomerapp.model.*;


public class NetworkManager {
	private static final String TAG = "NetworkManager";
	
	public static Customer LogOnCustomer(LoginModel loginModel) throws ClientProtocolException, IOException{
		try {
			Customer customer = null;
			
			//Create a client, a post request and paramenters arraylist
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(MainActivity.TM_LOGON_CUSTOMER);
			ArrayList<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
			
			//set request information
			parameters.add(new BasicNameValuePair("UserName", loginModel.getUserName()));
			parameters.add(new BasicNameValuePair("Password", loginModel.getPassword()));
			httpPost.setEntity(new UrlEncodedFormEntity(parameters));
			
			//execute the request
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//if response is ok
			if(httpResponse.getStatusLine().getStatusCode() == 200){
				//get response content
				HttpEntity httpEntity = httpResponse.getEntity();
				
				//convert the entity{the resulting page} to string and parse it
				String content = EntityUtils.toString(httpEntity, "UTF-8");
				
				Log.d(TAG, "content !!!!!!!!!!!!!!!" + content);
				if (content.equals("null")) {
					return null;
				}
				customer = parseTheContent(content);
			}
			
			return customer;

		} catch (Exception e) {
			Log.e(TAG, "Error occured while dowbloading", e);
			return null;
		}
		
	}

	private static Customer parseTheContent(String content) {
		// parse the content
		try {
			Customer customer = null;
			JSONObject customerJO = new JSONObject(content);
			
			int customerId =Integer.parseInt(customerJO.getString("CustomerId"));
			String customerName = customerJO.getString("CustomerName");
			String email = customerJO.getString("Email");
			String gsm= customerJO.getString("GSM");
			double lat = customerJO.getDouble("Lat");
			double lng = customerJO.getDouble("Lng");
			String password = customerJO.getString("Password");
			//String lastUpdate = customerJO.getString("LastUpdate");

			customer = new Customer(customerId, customerName, email, gsm, lat, lng, password);
			Log.d(TAG, "parsed customer object"+ customer.toString());
			return customer;
		} catch (Exception e) {
			Log.e(TAG, "Error on parsing "+e);
			return null;
		}
		
		
//		try{
//			JSONArray json = new JSONArray(content);
//	
//				ArrayList<Customer> customerList = new ArrayList<Customer>();				
//				//for each customer in json array
//				for(int i = 0; i < json.length(); i++){
//					JSONObject customerJO = json.getJSONObject(i);
//					
//					int customerId = customerJO.getInt("CustomerId");
//					String customerName = customerJO.getString("CustomerName");
//					String email = customerJO.getString("Email");
//					String cellPhone = customerJO.getString("CellPhone");
//					String picture = customerJO.getString("Picture");
//					double lat = customerJO.getDouble("CustomerLat");
//					double lng = customerJO.getDouble("CustomerLon");
//					String password = customerJO.getString("PAssword");
//					
//					customerList.add(new Customer(
//								customerId, customerName, 
//								email, cellPhone, picture,
//								lat, lng, password
//							));
//				}
//				
//			Log.d(TAG,"Parsed grouplist/n" + customerList);
//			return customerList.get(0);
//		}catch(Exception e){
//			Log.e(TAG, "Error occured while parsing customers", e);
//			return null;
//		}
//	}
	}
}
