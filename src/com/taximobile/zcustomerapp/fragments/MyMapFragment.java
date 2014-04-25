package com.taximobile.zcustomerapp.fragments;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.taximobile.zcustomerapp.R;
import com.taximobile.zcustomerapp.background.PositionManager;
import com.taximobile.zcustomerapp.background.PositionReceiver;
import com.taximobile.zcustomerapp.model.ModelManager;

public class MyMapFragment extends Fragment {
	private static final String TAG = "MyMapFragment";
	
	private PositionManager _positionManager;	
	private GoogleMap googleMap;
	private LatLng _currentLatLng;
	private Button getTaxiButton;
	
	private BroadcastReceiver _positionReceiver = new PositionReceiver(){
		@Override
		protected void onLocationReceived(Context context, Location loc) {
			if(isVisible() && loc != null){
				_currentLatLng = new LatLng(loc.getLatitude(), loc.getLongitude());
				updateUI();
				getMeTaxi();
			}
		};
		
		@Override
		protected void onProviderEnabledChanged(boolean enabled) {
			String toastText = enabled ? "GPS enabled" : "GPS disabled";
			Toast.makeText(getActivity(), toastText, Toast.LENGTH_LONG).show();
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//_currentLatLng= new LatLng(26.8568, 36.8526);
		_positionManager = PositionManager.Get(getActivity(), PositionManager.APP_TYPE_CUSTOMER);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_map, container, false);
		
		MapFragment MF = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map);
		
		googleMap =MF.getMap();
		initialMap();//initialize the better zoomed map
		
		getTaxiButton = (Button) view.findViewById(R.id.get_taxi_button);
		getTaxiButton.setEnabled(false);
		
		
		//start location updates
		//when location received, update _currentLatLng and updateUI()
		_positionManager.startLocationUpdates();
		
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		getActivity().registerReceiver(_positionReceiver,
				new IntentFilter(PositionManager.ACTION_LOCATION));
	}
	
	@Override
	public void onStop() {
		getActivity().unregisterReceiver(_positionReceiver);
		_positionManager.stopLocationUpdates();
		super.onStop();
	}
	
	private void getMeTaxi(){
		getTaxiButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(getActivity(), "getme Taxi clicked", Toast.LENGTH_LONG).show();
			}
		});
	}
	
	private void updateUI(){
		addCurrentPositionToMap();
		centerTheMap();
		zoomTheMap(16);
		getTaxiButton.setEnabled(true);
	}
	
	private void addCurrentPositionToMap() {
		googleMap.addMarker(new MarkerOptions()
        .position(_currentLatLng)
        .anchor(0.5f, 0.5f)
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_customer)));
	}
	
	private void centerTheMap(){
		CameraUpdate center = CameraUpdateFactory.newLatLng(_currentLatLng);
		googleMap.moveCamera(center);
	}
	
	private void zoomTheMap(int z){
		CameraUpdate zoom = CameraUpdateFactory.zoomTo(z);
		googleMap.moveCamera(zoom);
	}
	
	private void initialMap(){
		LatLng iLoc = new LatLng(39.09, 35.37);
		CameraUpdate center = CameraUpdateFactory.newLatLng(iLoc);
		googleMap.moveCamera(center);
		
		CameraUpdate zoom = CameraUpdateFactory.zoomTo(6);
		googleMap.moveCamera(zoom);
		
	}
	
}
