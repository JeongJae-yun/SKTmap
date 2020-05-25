package com.example.mvvm_tmap.model.response

data class MapResponse(
    val searchPoiInfo: SearchPoiInfo
)

data class SearchPoiInfo(
    val count: String,
    val page: String,
    val pois: Pois,
    val totalCount: String
)

data class Pois(
    val poi: List<Poi>
)

data class Poi(
    val buildingNo1: String,
    val buildingNo2: String,
    val dataKind: String,
    val detailAddrName: String,
    val firstNo: String,
    val frontLat: String,
    val frontLon: String,
    val ggPrice: String,
    val hhPrice: String,
    val highGpPrice: String,
    val highHhPrice: String,
    val highHhSale: String,
    val id: String,
    val llPrice: String,
    val lowerAddrName: String,
    val merchantFlag: String,
    val middleAddrName: String,
    val minOilYn: String,
    val mlClass: String,
    val name: String,
    val noorLat: String,
    val noorLon: String,
    val oilBaseSdt: String,
    val parkFlag: String,
    val radius: String,
    val roadName: String,
    val rpFlag: String,
    val secondNo: String,
    val stId: String,
    val telNo: String,
    val upperAddrName: String
)