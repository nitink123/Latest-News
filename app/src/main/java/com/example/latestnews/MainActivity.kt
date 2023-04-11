package com.example.latestnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.latestnews.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),handleonclick {
      lateinit var binding: ActivityMainBinding
      lateinit var adapter: NewsAdapter
      private var list = mutableListOf<Article>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView( binding.root)

//        binding.animationView.setAnimation("news.json")
//        binding.animationView.playAnimation()
        adapter = NewsAdapter(this@MainActivity, list,this)

        val layoutManager = StackLayoutManager()
   //     binding.mainRv.layoutManager = LinearLayoutManager(this)
        layoutManager.horizontalLayout
        binding.mainRv.layoutManager = layoutManager
            binding.mainRv.adapter = adapter


      getNews()

    }

    private fun getNews() {
      //  val news : NewsService.newInstance.get
    //    val newsApi = NewsService.newInstance.getNews("in",1)
        val newsApi = NewsService.newInstance.getNews("in")
      newsApi.enqueue(object : Callback<newsData>{
          override fun onResponse(call: Call<newsData>, response: Response<newsData>) {
              Log.e("response","response mai enter kr gaya")
              val result  = response.body()
             if(result != null) {
                 list.addAll(result.articles)
                 adapter.notifyDataSetChanged()
             }
          }

          override fun onFailure(call: Call<newsData>, t: Throwable) {
              Log.e("failure","failure mai enter kr gaya")
             Toast.makeText(this@MainActivity,"response is not generated",Toast.LENGTH_LONG)
          }

      })
    }

    override fun onBackPressed() {
        binding.webviewDesc.visibility = View.GONE
        binding.webviewDesc.removeAllViews()
//      if(binding.webviewDesc.canGoBack()){
//          binding.webviewDesc.goBack()
//      }else{
//        //  super.onBackPressed()
//          binding.webviewDesc.visibility = View.GONE
//      }
    }

    override fun handleclick(url: String) {

        binding.webviewDesc.visibility = View.VISIBLE
        binding.webviewDesc.webViewClient = WebViewClient()
        binding.webviewDesc.loadUrl(url)

       // do nothing
    }
}