package com.example.familybrowser

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class TabPagerAdapter(fm:FragmentManager, private var tabCount:Int):FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {

        return when (position) {
            0 -> DetailsProductFragment()
            1 -> DetailsSizeFragment()
            2 -> DetailsProductFragment()
            3 -> DetailsProductFragment()
            4 -> DetailsProductFragment()
            else -> null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}