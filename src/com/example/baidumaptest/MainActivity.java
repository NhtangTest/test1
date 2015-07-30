package com.example.baidumaptest;

import java.util.List;

import com.baidu.lbsapi.BMapManager;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.nplatform.comapi.basestruct.GeoPoint;
import com.baidu.nplatform.comapi.map.MapController;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity { 
	
	private LocationMode mCurrentMode;
    MapView mMapView = null;
    
    private LocationManager locationManager;
    private String provider;
    
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);   
        //��ʹ��SDK�����֮ǰ��ʼ��context��Ϣ������ApplicationContext  
        //ע��÷���Ҫ��setContentView����֮ǰʵ��  
        SDKInitializer.initialize(getApplicationContext());  
        setContentView(R.layout.activity_main);  
        //��ȡ��ͼ�ؼ�����  
        mMapView = (MapView) findViewById(R.id.map_view);
        BaiduMap mBaiduMap = mMapView.getMap();  
      //��ͨ��ͼ  
      mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); 
      
      locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
      List<String> providerList = locationManager.getProviders(true);
      if(providerList.contains(LocationManager.GPS_PROVIDER)){
    	  provider = LocationManager.GPS_PROVIDER;
    	  Toast.makeText(this, "GPSλ���ṩ����", Toast.LENGTH_SHORT).show();
      }else if(providerList.contains(LocationManager.NETWORK_PROVIDER)){
    	  provider = LocationManager.NETWORK_PROVIDER;
    	  Toast.makeText(this, "NET_WORKλ���ṩ����", Toast.LENGTH_SHORT).show();
      }else {
    	  Toast.makeText(this, "δ��ʼλ���ṩ����", Toast.LENGTH_SHORT).show();
    	  return;
      }
      
      Location location = locationManager.getLastKnownLocation(provider);
      if (location != null){
    	// ������λͼ��  
      	mBaiduMap.setMyLocationEnabled(true);  
      	// ���춨λ����  
      	MyLocationData locData = new MyLocationData.Builder() 
      	    // �˴����ÿ����߻�ȡ���ķ�����Ϣ��˳ʱ��0-360  
      	    .direction(100).latitude(location.getLatitude())  
      	    .longitude(location.getLongitude()).build();  
      	// ���ö�λ����  
      	mBaiduMap.setMyLocationData(locData);  
      	// ���ö�λͼ������ã���λģʽ���Ƿ���������Ϣ���û��Զ��嶨λͼ�꣩  
      	BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.ioc_geo);  
      	MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);  
      	mBaiduMap.getLocationConfigeration();  
      	// ������Ҫ��λͼ��ʱ�رն�λͼ��  
      	mBaiduMap.setMyLocationEnabled(false);
      }
    }  
    
    
    private void navigateTo(Location location) {
		// TODO Auto-generated method stub
		//MapController controller = mMapView.getContronller();
		//controller.setZoom(16);
		//GeoPoint point = new GeoPoint((int)(location.getLatitude()*1E6), (int)(location.getLongitude()*1E6));
		//controller.setCenter(point);
    	
    	//MyLocationData myLocationOverlay = new MyLocationData(mMapView);
    	Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
    	
	}


	@Override  
    protected void onDestroy() {  
        super.onDestroy();  
        //��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���  
        mMapView.onDestroy();  
    }  
    @Override  
    protected void onResume() {  
        super.onResume();  
        //��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���  
        mMapView.onResume();  
        }  
    @Override  
    protected void onPause() {  
        super.onPause();  
        //��activityִ��onPauseʱִ��mMapView. onPause ()��ʵ�ֵ�ͼ�������ڹ���  
        mMapView.onPause();  
        }  
    }
