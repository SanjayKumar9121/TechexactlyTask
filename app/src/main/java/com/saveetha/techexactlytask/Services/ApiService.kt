package com.saveetha.techexactlytask.Services

import com.saveetha.techexactlytask.Model.ApplicationModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("g-mee-api/api/v1/apps/list")
    fun getAllApps(
        @Field("kid_id") kId: String
    ): retrofit2.Call<ApplicationModel>

}