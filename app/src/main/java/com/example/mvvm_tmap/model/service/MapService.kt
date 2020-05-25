package com.example.mvvm_tmap.model.service

import android.util.Log
import com.example.mvvm_tmap.model.data.DataResponse
import com.skt.Tmap.TMapData
import com.skt.Tmap.TMapPoint
import kotlin.collections.ArrayList

open class MapService {
    companion object{
        private val tmapdata = TMapData()
        private var  poiList = ArrayList<DataResponse>()
    }


    fun getData(point:TMapPoint, name : String) : ArrayList<DataResponse>{
        tmapdata.findAroundNamePOI(point,name,1,5,
            TMapData.FindAroundNamePOIListenerCallback { it ->
                try{
                    it.forEach {
                        poiList.add(DataResponse(it.poiName,it.poiPoint.latitude,it.poiPoint.longitude))  //저장
                        Log.d("Service test : ", "${it.poiName} | ${it.poiPoint.latitude} | ${it.poiPoint.longitude}")
                    }
                }catch (e : Exception){
                    e.printStackTrace()
                }
            })
        return poiList
    }






}