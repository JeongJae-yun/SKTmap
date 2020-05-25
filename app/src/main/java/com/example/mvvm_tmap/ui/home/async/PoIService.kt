package com.example.mvvm_tmap.ui.home.async

import android.util.Log
import com.example.mvvm_tmap.model.data.DataResponse
import com.skt.Tmap.TMapData
import com.skt.Tmap.TMapPoint
import java.lang.Exception

class PoIService {

    companion object{
        var poiList = ArrayList<DataResponse>()
        val pointList = ArrayList<DataResponse>()

        private val tmapdata = TMapData()
    }

    fun getMapsData(point: TMapPoint, name: String) : ArrayList<DataResponse>{
        tmapdata.findAroundNamePOI(point,name,1,20,
            TMapData.FindAroundNamePOIListenerCallback {
                try{
                    it.forEach {
                        pointList.add(DataResponse(it.poiName,it.poiPoint.latitude,it.poiPoint.longitude))  //저장
                        Log.d("data test", "${it.poiName} | ${it.poiPoint.latitude} | ${it.poiPoint.longitude}")
                    }

                }catch (e : Exception){
                    e.printStackTrace()
                }
            })
        return pointList
    }
}