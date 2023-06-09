package com.example.latestnews

import android.app.usage.NetworkStats
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.hardware.SensorManager
import android.net.Network
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.OrientationEventListener
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latestnews.databinding.ActivityMainBinding
import kotlinx.coroutines.coroutineScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale.Builder

class MainActivity : AppCompatActivity(),handleonclick {
      lateinit var binding: ActivityMainBinding
      lateinit var adapter: NewsAdapter
      lateinit var newsVM: newsVM
      private var list = mutableListOf<Article>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView( binding.root)

//        binding.animationView.setAnimation("news.json")
//        binding.animationView.playAnimation()


        adapter = NewsAdapter(this@MainActivity, list,this)

//        val layoutManager = StackLayoutManager()
//        layoutManager.horizontalLayout

        // observing data
        binding.mainRv.layoutManager = LinearLayoutManager(this)
            binding.mainRv.adapter = adapter


        newsVM = newsVM(application)
        newsVM = ViewModelProvider(this)[newsVM::class.java]

        binding.button.setOnClickListener {
            binding.button.visibility = View.GONE
            val intent  = Intent(this,tablayoutActivity::class.java)
            startActivity(intent)
        }


        newsVM.newsResponse.observe(this, Observer { result ->
            val ans = result.body()
            if (ans != null) {
                binding.progressCircular.visibility = View.GONE
                binding.button.visibility = View.VISIBLE
                list.addAll(ans.articles)
                adapter.notifyDataSetChanged()
            } else {
              //  Buil
                 Toast.makeText(this@MainActivity,"response is not generated", Toast.LENGTH_LONG)
                // Handle null result, if needed
            }
        })


        newsVM.getNews()

    }


    override fun onResume() {

        super.onResume()
        binding.button.visibility = View.VISIBLE
    }

    override fun onBackPressed() {

            binding.webviewDesc.visibility = View.GONE
            binding.webviewDesc.removeAllViews()
           binding.button.visibility = View.VISIBLE

    }

    override fun handleclick(url: String) {
        binding.webviewDesc.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                binding.progressCircular.visibility = View.VISIBLE// show the progress bar
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                binding.progressCircular.visibility = View.GONE
                binding.button.visibility = View.GONE
                //   binding.progressBar.visibility = View.GONE // hide the progress bar
            }
        }

        binding.webviewDesc.visibility = View.VISIBLE
        binding.webviewDesc.loadUrl(url)
    }
}