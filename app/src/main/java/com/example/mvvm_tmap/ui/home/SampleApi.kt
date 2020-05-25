package com.example.mvvm_tmap.ui.home

import android.util.Log
import android.widget.Toast
import com.example.mvvm_tmap.model.data.DataResponse
import com.example.mvvm_tmap.util.App
import com.skt.Tmap.TMapData
import com.skt.Tmap.TMapPoint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception


class SampleApi(private val point:TMapPoint,
                private val name:String) {

    companion object{
        var poiList = ArrayList<DataResponse>()
        var context = App.instance.context()
        private val tmapdata = TMapData()
    }

    lateinit var backgroundtask: Disposable

    fun BackgroundTask() { //onPreExecute
        backgroundtask =
            Observable.fromCallable {
                //doInBackground
                poiList.clear()
                tmapdata.findAroundNamePOI(point,name,1,20,
                    TMapData.FindAroundNamePOIListenerCallback { it ->
                        try{
                            it.forEach {
                                poiList.add(DataResponse(it.poiName,it.poiPoint.latitude,it.poiPoint.longitude))  //저장
                            }

                            poiList.forEach {
                                Log.d("TEST ","${it.name} | ${it.lat} | ${it.lon}")
                            }
                            //여기 어디선가 single톤으로 데이터 저장...필요

                        }catch (e : Exception){
                            e.printStackTrace()
                        }
                    })
                Log.d("로그 doInBackground", "title")
                false
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    //onPostExecute
                    Toast.makeText(context,"완료",Toast.LENGTH_SHORT).show()
                    backgroundtask.dispose()
                }
    }

}