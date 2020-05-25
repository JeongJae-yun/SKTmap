package com.example.mvvm_tmap.model.dataModel

import com.example.mvvm_tmap.model.data.DataResponse
import com.example.mvvm_tmap.model.response.MapResponse
import com.example.mvvm_tmap.model.service.DataService
import com.example.mvvm_tmap.model.service.MapService
import com.skt.Tmap.TMapPoint
import io.reactivex.Single

class MapDataModelImpl(private val service2 : DataService) : MapDataModel {
  /*  override fun getData(point: TMapPoint, name: String): ArrayList<DataResponse> {
        return service.getData(point, name)
    }*/

    override fun getMapData(
        version: Int,
        page: Int,
        count: Int,
        categories: String,
        centerLon: Double,
        centerLat: Double,
        radius: Int,
        appkey: String
    ): Single<MapResponse> {
        return service2.getNowMovie(version =version,page = page,count = count, categories = categories, centerLon = centerLon, centerLat = centerLat, radius = radius, api_key = appkey)

    }


}