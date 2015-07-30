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
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext  
        //注意该方法要再setContentView方法之前实现  
        SDKInitializer.initialize(getApplicationContext());  
        setContentView(R.layout.activity_main);  
        //获取地图控件引用  
        mMapView = (MapView) findViewById(R.id.map_view);
        BaiduMap mBaiduMap = mMapView.getMap();  
      //普通地图  
      mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); 
      
      locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
      List<String> providerList = locationManager.getProviders(true);
      if(providerList.contains(LocationManager.GPS_PROVIDER)){
    	  provider = LocationManager.GPS_PROVIDER;
    	  Toast.makeText(this, "GPS位置提供器！", Toast.LENGTH_SHORT).show();
      }else if(providerList.contains(LocationManager.NETWORK_PROVIDER)){
    	  provider = LocationManager.NETWORK_PROVIDER;
    	  Toast.makeText(this, "NET_WORK位置提供器！", Toast.LENGTH_SHORT).show();
      }else {
    	  Toast.makeText(this, "未开始位置提供器！", Toast.LENGTH_SHORT).show();
    	  return;
      }
      
      Location location = locationManager.getLastKnownLocation(provider);
      if (location != null){
    	// 开启定位图层  
      	mBaiduMap.setMyLocationEnabled(true);  
      	// 构造定位数据  
      	MyLocationData locData = new MyLocationData.Builder() 
      	    // 此处设置开发者获取到的方向信息，顺时针0-360  
      	    .direction(100).latitude(location.getLatitude())  
      	    .longitude(location.getLongitude()).build();  
      	// 设置定位数据  
      	mBaiduMap.setMyLocationData(locData);  
      	// 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）  
      	BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.ioc_geo);  
      	MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);  
      	mBaiduMap.getLocationConfigeration();  
      	// 当不需要定位图层时关闭定位图层  
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
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理  
        mMapView.onDestroy();  
    }  
    @Override  
    protected void onResume() {  
        super.onResume();  
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理  
        mMapView.onResume();  
        }  
    @Override  
    protected void onPause() {  
        super.onPause();  
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理  
        mMapView.onPause();  
        }  
    }
