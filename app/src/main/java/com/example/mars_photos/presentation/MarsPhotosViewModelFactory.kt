package com.example.mars_photos.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mars_photos.data.MarsPhotosRepository
import com.example.mars_photos.domain.GetMarsPhotoUseCase
import javax.inject.Inject

class MarsPhotosViewModelFactory @Inject constructor(
    private val marsPhotosViewModel: MarsPhotosViewModel
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MarsPhotosViewModel::class.java)){
            return marsPhotosViewModel as T
        }else
            throw IllegalArgumentException("Unknown class name")

    }
}