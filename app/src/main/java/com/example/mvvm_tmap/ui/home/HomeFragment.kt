package com.example.mvvm_tmap.ui.home

import android.graphics.BitmapFactory
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.mvvm_tmap.R
import com.example.mvvm_tmap.base.BaseViewFragment
import com.example.mvvm_tmap.databinding.FragmentHomeBinding
import com.example.mvvm_tmap.util.permission
import com.skt.Tmap.TMapMarkerItem
import com.skt.Tmap.TMapPoint
import com.skt.Tmap.TMapView
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseViewFragment<FragmentHomeBinding, HomeViewModel>() {
    companion object{
        lateinit var tMapPoint : TMapPoint
        lateinit var mapView: TMapView
    }
    private val mapSetting = MapSetting()

    override val layoutResourceId: Int
        get() = R.layout.fragment_home

    override val viewModel: HomeViewModel by viewModel()

    override fun initStartView() {
        mapView = TMapView(context) //mapview 재정의

        mapView.run {
            setSKTMapApiKey("l7xx018af8f0f00743e8ac9cc583dcbf19d4")
            zoomLevel = 15
            mapType = TMapView.MAPTYPE_STANDARD
            setLanguage(TMapView.LANGUAGE_KOREAN)
            setCompassMode(true)
            setIconVisibility(true)
        }

        HomeMapView.addView(mapView)

        /*Permission Check*/
        val permission = permission()
        val status : Boolean = permission.checkStatus(requireContext())

        if (status){ mapSetting.loadMyLocation(mapView, requireActivity())}
        else{ Toast.makeText(context,"위치 권한 동의 먼저", Toast.LENGTH_SHORT).show()}

        tMapPoint = mapView.centerPoint
    }

    override fun initDataBinding() {
        val bitmapIcon = BitmapFactory.decodeResource(resources, R.drawable.boxicon)

        //Adapter에 넣고 notifychange
        viewModel.dataResponseLiveData.observe(viewLifecycleOwner, Observer {
            var i = 0
            it.searchPoiInfo.pois.poi.forEach {
                Log.d("Fragment TEst : ", "${it.name} || ${it.frontLat} || ${it.frontLon}")
                val markerItem = TMapMarkerItem()
                markerItem.run {
                    icon = bitmapIcon
                    tMapPoint = TMapPoint(it.frontLat.toDouble(), it.frontLon.toDouble())
                    name = it.name
                }
                mapView.addMarkerItem("markerItem add $i", markerItem)
                i++
            }
            val centerPoint : TMapPoint = mapView.centerPoint //현재 보이는 맵 가운데 좌표 가져오기.
            mapView.setCenterPoint(centerPoint.longitude,centerPoint.latitude,true)
        })
    }

    override fun initAfterBinding() {
        val point = mapView.centerPoint
        val categoryName = "편의점"
        btnFind.setOnClickListener {
            mapView.removeAllMarkerItem()
            /*viewModel.getmapdata(point, categoryName)*/
            Log.d("내 위치 : ", "${point.longitude} || ${point.latitude}")
            viewModel.getData(1,1,10, categoryName, point.longitude, point.latitude,1,"l7xx018af8f0f00743e8ac9cc583dcbf19d4")
        }
    }


}
