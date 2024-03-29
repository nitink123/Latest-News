package com.example.latestnews

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class newsVM(application: Application) :  AndroidViewModel(application)  {
    val newsResponse:MutableLiveData<Response<newsData>> = MutableLiveData()

    val newsResponseCategory:MutableLiveData<Response<newsData>> = MutableLiveData()

    fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
            val newsApi = NewsService.newInstance.getNews("in")
            newsApi.enqueue(object : Callback<newsData> {
                override fun onResponse(call: Call<newsData>, response: Response<newsData>) {
                    Log.e("response", "response mai enter kr gaya")
                    val result = response.body()
                    if (result != null) {
                        newsResponse.value = response
                    }
                }

                override fun onFailure(call: Call<newsData>, t: Throwable) {
                    Log.e("failure", "failure mai enter kr gaya")

                    //  Toast.makeText(this@MainActivity,"response is not generated", Toast.LENGTH_LONG)
                }

            })
        }
    }

    fun getNewsCategoryWise(string: String){
        viewModelScope.launch (Dispatchers.IO) {
            val newsApi = NewsService.newInstance.getNewsCate0goryWise("in", string)
            newsApi.enqueue(object : Callback<newsData> {
                override fun onResponse(call: Call<newsData>, response: Response<newsData>) {
                    Log.e("response", "response mai enter kr gaya")
                    val result = response.body()
                    if (result != null) {
                        newsResponseCategory.value = response
                    }
                }

                override fun onFailure(call: Call<newsData>, t: Throwable) {
                    Log.e("failure", "failure mai enter kr gaya")

                    //  Toast.makeText(this@MainActivity,"response is not generated", Toast.LENGTH_LONG)
                }

            })
        }
    }
}