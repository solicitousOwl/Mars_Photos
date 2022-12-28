package com.example.mars_photos.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mars_photos.data.MarsPhotosArrayDto
import com.example.mars_photos.domain.GetMarsPhotoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MarsPhotosViewModel @Inject constructor(
    val getMarsPhotoUseCase: GetMarsPhotoUseCase
) : ViewModel() {

    init {
        loadPhotos()
    }

    private var _isLoading = MutableStateFlow(false)
    var isLoading = _isLoading.asStateFlow()

    private var _marsPhotos =
        MutableStateFlow<Array<MarsPhotosArrayDto>>(emptyArray())
    var marsPhotos = _marsPhotos.asStateFlow()

    fun loadPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _isLoading.value = true
                getMarsPhotoUseCase.execute()
            }.fold(
                onSuccess = {
                    _marsPhotos.value = it
                },
                onFailure = {
                    Log.d("MarsPhotosViewModel", "loadPhotos: ${it.message ?: ""}")
                }
            )
            _isLoading.value = false
        }
    }
}