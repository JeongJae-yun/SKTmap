package com.example.mvvm_tmap.model.service

import com.example.mvvm_tmap.model.response.MapResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface DataService{

/*
    https://apis.openapi.sk.com/tmap/pois/search/around?version=1&page=1&count=10&categories=카페
    &centerLon=126.97840230814947&centerLat=37.566686604063214&radius=1&appKey=l7xx018af8f0f00743e8ac9cc583dcbf19d4
*/
    @GET("tmap/pois/search/around")
    fun getNowMovie(
        @Query("version") version:Int,
        @Query("page") page:Int,
        @Query("count") count:Int,
        @Query("categories") categories : String,
        @Query("centerLon") centerLon : Double,
        @Query("centerLat") centerLat : Double,
        @Query("radius") radius : Int,
        @Query("appkey") api_key:String
    ): Single<MapResponse>

}