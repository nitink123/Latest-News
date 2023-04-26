package com.example.latestnews

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class viewpagerAdapter(fragmentManager: FragmentManager,context: Context) : FragmentPagerAdapter(fragmentManager) {


    override fun getCount(): Int {
       return 6;
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
               return ViewPagerFragment.newInstance("business")
            }
            1 -> {
                return ViewPagerFragment.newInstance("entertainment")
            }
            2 -> {
                return ViewPagerFragment.newInstance("health")
            }
            3 -> {
                return ViewPagerFragment.newInstance("science")
            }
            4 -> {
                return ViewPagerFragment.newInstance("sports")
            }
            else -> {
                return ViewPagerFragment.newInstance("technology")
            }
        }

    }



    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return "business"
            }
            1 -> {
                return "entertainment"
            }
            2 -> {
                return "health"
            }
            3 -> {
                return "science"
            }
            4 -> {
                return "sports"
            }
            else -> {
                return "technology"
            }
        }
    }






}