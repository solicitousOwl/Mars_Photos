package com.example.mars_photos.data

import com.example.mars_photos.entity.MarsPhotosArray
import kotlinx.coroutines.delay
import javax.inject.Inject

class MarsPhotosRepository @Inject constructor() {
    suspend fun getMarsPhotos(): Array<MarsPhotosArrayDto> {
        delay(2000)
        return RetrofitInstance.searchMarsPhotosApi.getMarsPhotos().photos
    }

}