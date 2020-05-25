package com.example.mvvm_tmap

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mvvm_tmap.ui.dashboard.DashboardFragment
import com.example.mvvm_tmap.ui.home.HomeFragment
import com.example.mvvm_tmap.ui.notifications.NotificationsFragment
import com.example.mvvm_tmap.util.permission
import com.example.mvvm_tmap.util.replace

class MainActivity : AppCompatActivity() {
    //퍼미션 사용
    private lateinit var permission : permission
    private val homeFragment : HomeFragment by lazy {
        HomeFragment()
    }

    private val dashboardFragment: DashboardFragment by lazy{
        DashboardFragment()
    }

    private val notificationsFragment : NotificationsFragment by lazy{
        NotificationsFragment()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item->
        when(item.itemId){
            R.id.navigation_home->{
                replace(R.id.container, homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard->{
                replace(R.id.container, dashboardFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications->{
                replace(R.id.container, notificationsFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainCheckPermission()

        replace(R.id.container, homeFragment)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun mainCheckPermission(){
        permission = permission()
        permission.checkPermission(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        permission.handlePermissionsResult(requestCode,permissions, grantResults)
    }

}
