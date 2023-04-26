package com.example.latestnews

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.latestnews.databinding.TablayoutScreenBinding
import com.google.android.material.tabs.TabLayout


class tablayoutActivity : AppCompatActivity() {

    lateinit var binding: TablayoutScreenBinding
    lateinit var viewModel: newsVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = TablayoutScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = viewpagerAdapter(supportFragmentManager,this)
        binding.viewpager.adapter = adapter
        binding.tabMode.setupWithViewPager(binding.viewpager)

        viewModel = newsVM(application)
        viewModel = ViewModelProvider(this)[newsVM::class.java]

        binding.tabMode.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {


                // Change the background color of the TabLayout when a tab is selected
                when (tab.position) {
                    0 -> {
                        binding.tabMode.setBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.black
                            )
                        )
                        binding.viewpager.setBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.black
                            )
                        )
                    }
                    1 -> {
                        binding.tabMode.setBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.purple_200
                            )
                        )
                        binding.viewpager.setBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.purple_200
                            )
                        )
                    }
                    2 -> {
                        binding.tabMode.setBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.purple_500
                            )
                        )
                        binding.viewpager.setBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.purple_500
                            )
                        )
                    }
                    3 -> {
                        binding.tabMode.setBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.white
                            )
                        )
                        binding.viewpager.setBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.white
                            )
                        )
                    }
                    4 -> {
                        binding.tabMode.setBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.teal_200
                            )
                        )
                        binding.viewpager.setBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.teal_200
                            )
                        )
                    }
                    5 -> {
                        binding.tabMode.setBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.teal_700
                            )
                        )
                        binding.viewpager.setBackgroundColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.teal_700
                            )
                        )
                    }
                    // Add more cases for other tabs if needed
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })



    }



    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment != null && fragment is ViewPagerFragment) {
            fragment.onbackPressed()
      //      supportFragmentManager.popBackStack()
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment_container_view_tag,ViewPagerFragment())
//                .commit()
        } else {
            super.onBackPressed()
        }
    }

}