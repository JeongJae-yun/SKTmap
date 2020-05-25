package com.example.mvvm_tmap.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.example.mvvm_tmap.base.BaseViewModel
import com.example.mvvm_tmap.model.data.DataResponse
import com.example.mvvm_tmap.model.dataModel.MapDataModel
import com.example.mvvm_tmap.model.response.MapResponse
import com.skt.Tmap.TMapPoint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.collections.ArrayList

class HomeViewModel (private val model : MapDataModel): BaseViewModel() {

    var poiList = ArrayList<DataResponse>()

    private val _mapResponseLiveData = MutableLiveData<MapResponse>()
    val dataResponseLiveData: LiveData<MapResponse>
        get() = _mapResponseLiveData

    fun getData(version:Int, page:Int, count:Int, categories:String, centerLon:Double, centerLat:Double,radius:Int, appkey:String){
        addDisposable(model.getMapData(version, page, count, categories, centerLon, centerLat, radius, appkey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                it.run {
                    this.searchPoiInfo.pois.poi.forEach {
                        Log.d("Example ViewModel : ", "$it.name")
                        _mapResponseLiveData.postValue(this)
                    }
                }
            }, {
                Log.d("TAG", "response error, message : ${it.localizedMessage}")

            }))

    }


   /* fun getmapdata(point: TMapPoint,name: String) {
        //poiList = model.getData(point, name)
        addDisposable(Observable.fromArray(model.getData(point, name))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _mapResponseLiveData.postValue(it)
                it.forEach {data ->
                    Log.d("VIEW MODEL : ", "${data.name} | ${data.lat} | ${data.lon}")
                }
            },{
                Log.d("ERROR  : " , "DISS MISS ERROR ${it.localizedMessage}")
            }))
    }*/


}