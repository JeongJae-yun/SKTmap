package com.example.mvvm_tmap.di

import com.example.mvvm_tmap.model.dataModel.MapDataModel
import com.example.mvvm_tmap.model.dataModel.MapDataModelImpl
import com.example.mvvm_tmap.model.service.DataService
import com.example.mvvm_tmap.model.service.MapService
import com.example.mvvm_tmap.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

var retrofitMap = module{
    single<DataService>{
        Retrofit.Builder()
            .baseUrl("https://apis.openapi.sk.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DataService::class.java)
    }
}

var viewModelPart = module{
    viewModel { HomeViewModel(get()) }
}

var dataModel = module {
    factory<MapDataModel>{ MapDataModelImpl(get())}
}

var service = module {
    factory<MapService> { MapService() }
}

var myDiModule
        = listOf(retrofitMap, viewModelPart, dataModel, service)