package br.com.braspag.internal.network

import br.com.braspag.internal.data.EnrollData
import br.com.braspag.internal.network.dto.*
import retrofit2.Call
import retrofit2.http.*

internal interface BraspagApi {
    @POST("v2/3ds/init")
    @Headers("Content-Type: application/json")
    fun getJWT(
        @Header("Authorization") authorization: String,
        @Body request: RequestOrder
    ): Call<ResponseJWT>

    @POST("v2/3ds/enroll")
    @Headers("Content-Type: application/json")
    fun enroll(
        @Header("Authorization") authorization: String,
        @Body request: EnrollData
    ): Call<ResponseEnroll>

    @POST("v2/3ds/validate")
    @Headers("Content-Type: application/json")
    fun validate(
        @Header("Authorization") authorization: String,
        @Body request: RequestValidate
    ): Call<Authentication>
}