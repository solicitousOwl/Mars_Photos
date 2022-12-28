package com.example.mars_photos.domain

import android.util.Log
import com.example.mars_photos.data.MarsPhotosArrayDto
import com.example.mars_photos.data.MarsPhotosDto
import com.example.mars_photos.data.MarsPhotosRepository
import com.example.mars_photos.entity.MarsPhotosArray
import javax.inject.Inject

class GetMarsPhotoUseCase @Inject constructor(
    private val repository: MarsPhotosRepository
) {
    suspend fun execute(): Array<MarsPhotosArrayDto> {
        val response = repository.getMarsPhotos()
        Log.d("aslan555", "execute: ${response.size}")

        return response

    }


}