package br.com.braspag.internal.network

import br.com.braspag.internal.data.EnrollData
import br.com.braspag.internal.network.dto.Authentication
import br.com.braspag.internal.network.dto.RequestOrder
import br.com.braspag.internal.network.dto.RequestValidate
import br.com.braspag.internal.network.dto.ResponseEnroll
import br.com.braspag.internal.network.dto.ResponseJWT
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

internal interface BraspagApi {
    @POST("v2/3ds/init")
    @Headers("Content-Type: application/json")
    fun getJWT(
        @Header("Authorization") authorization: String,
        @Body request: RequestOrder,
    ): Call<ResponseJWT>

    @POST("v2/3ds/enroll")
    @Headers("Content-Type: application/json")
    fun enroll(
        @Header("Authorization") authorization: String,
        @Header("x-sdk-version") xSdkVersion: String,
        @Body request: EnrollData,
    ): Call<ResponseEnroll>

    @POST("v2/3ds/validate")
    @Headers("Content-Type: application/json")
    fun validate(
        @Header("Authorization") authorization: String,
        @Body request: RequestValidate,
    ): Call<Authentication>
}
