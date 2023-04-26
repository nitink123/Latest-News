package com.example.latestnews

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.latestnews.databinding.TablayoutScreenBinding


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



    }


}