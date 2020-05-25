package com.example.mvvm_tmap.ui.home

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mvvm_tmap.MyApplication
import com.skt.Tmap.TMapGpsManager
import com.skt.Tmap.TMapView


class MapSetting {

    private val requiredPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    companion object{
        val context = MyApplication.instance.context()
        val locationManager : LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    fun loadMyLocation(mapView: TMapView, activity : Activity) {
        val tmapGps = TMapGpsManager(activity)
        tmapGps.run {
            minTime = 1000
            minDistance = 5F
            provider = TMapGpsManager.LOCATION_SERVICE
            OpenGps()
        }

        val locationListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                val latitude = location.latitude
                val longitude = location.longitude
                mapView.setCenterPoint(longitude, latitude)
                mapView.setLocationPoint(longitude, latitude)
            }
            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        locationManager.run {
            checkPermission()
            requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
            requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f, locationListener)
        }

        mapView.run {
            setTrackingMode(true)
            setSightVisible(true)
        }
    }


    fun checkPermission() {
        val rejectedPermissionList = ArrayList<String>() //거절되었거나 아직 수락하지 않은 권한(퍼미션)을 저장할 문자열 배열 리스트
        val context = MyApplication.instance.context()
        for (permission in requiredPermissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED){
                //만약 권한이 없다면 rejectedPermissionList에 추가
                rejectedPermissionList.add(permission)
            }
        }
        //거절된 퍼미션이 있다면...
        if (rejectedPermissionList.isNotEmpty()) {
            //권한 요청
            val array = arrayOfNulls<String>(rejectedPermissionList.size)
            ActivityCompat.requestPermissions(context as Activity, rejectedPermissionList.toArray(array), 1)
        }else{
            //권한 있음
        }
    }
}