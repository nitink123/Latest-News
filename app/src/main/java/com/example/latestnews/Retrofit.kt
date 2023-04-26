package com.example.latestnews

import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit

// api_key   f16487c1cb4545448f292aaa4b3a59d3
// api to get result   https://newsapi.org/v2/top-headlines?country=in&apiKey=f16487c1cb4545448f292aaa4b3a59d3

const val API_KEY = "f16487c1cb4545448f292aaa4b3a59d3"
const val BASE_URL = "https://newsapi.org/"
interface Retrofit {


   @GET("v2/top-headlines?apiKey=$API_KEY")
   fun getNews(@Query("country") country : String) : Call<newsData>

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getNewsCate0goryWise(@Query("country") country : String,@Query("category") category: String) : Call<newsData>
}

object NewsService {
    val newInstance: com.example.latestnews.Retrofit
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newInstance = retrofit.create(com.example.latestnews.Retrofit::class.java)
    }
}