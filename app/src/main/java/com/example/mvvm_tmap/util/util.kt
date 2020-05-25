package com.example.mvvm_tmap.util

import android.content.Context
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity

//replace 확장
fun AppCompatActivity.replace(@IdRes frameId: Int, fragment: androidx.fragment.app.Fragment, tag: String? = null) {
    supportFragmentManager.beginTransaction().replace(frameId, fragment, tag).commit()
}

