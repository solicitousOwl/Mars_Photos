package com.example.mars_photos.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Headers

private const val BASE_URL="https://api.nasa.gov/"
private const val DEMO_KEY="CP4T4shl7utNJ5OBpHrKf8haiXhhp49zqDLI6O3l"

object RetrofitInstance{
    private val retrofit= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val searchMarsPhotosApi:SearchMarsPhotosApi=retrofit.create(SearchMarsPhotosApi::class.java)

}

interface SearchMarsPhotosApi{
    @GET("mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=$DEMO_KEY")
    suspend fun getMarsPhotos():MarsPhotosDto



}