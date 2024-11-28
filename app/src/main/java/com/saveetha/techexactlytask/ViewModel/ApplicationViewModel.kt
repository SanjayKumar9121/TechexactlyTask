package com.saveetha.techexactlytask.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saveetha.techexactlytask.Model.ApplicationModel
import com.saveetha.techexactlytask.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApplicationViewModel: ViewModel() {

    var apiresponses= MutableLiveData<ApplicationModel>()

    fun getAllApplicationsApi() {
        val apiService = RetrofitClient.instance
        apiService.getAllApps("378").enqueue(object : Callback<ApplicationModel> {
            override fun onResponse(
                call: Call<ApplicationModel>,
                response: Response<ApplicationModel>
            ) {
                if (response.isSuccessful) {
                    val allApps = response.body()
                    println(allApps)
                    apiresponses.value = allApps!!
                } else {
                    Log.e("TAG", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ApplicationModel>, t: Throwable) {
                Log.e("TAG", "Error fetching Application Lists: $t")
            }

        })
    }
}