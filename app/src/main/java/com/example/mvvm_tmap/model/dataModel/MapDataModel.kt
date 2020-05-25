package com.example.mvvm_tmap.model.dataModel

import com.example.mvvm_tmap.model.data.DataResponse
import com.example.mvvm_tmap.model.response.MapResponse
import io.reactivex.Single
import com.skt.Tmap.TMapPoint

interface MapDataModel{
    /*fun getData(point:TMapPoint, name:String): ArrayList<DataResponse>*/

    fun getMapData(version:Int, page:Int, count :Int, categories:String, centerLon:Double, centerLat:Double, radius:Int, appkey:String) : Single<MapResponse>
}