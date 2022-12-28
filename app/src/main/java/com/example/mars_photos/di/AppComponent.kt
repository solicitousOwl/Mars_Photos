package com.example.mars_photos.di

import com.example.mars_photos.presentation.MarsPhotosViewModel
import com.example.mars_photos.presentation.MarsPhotosViewModelFactory
import dagger.Component


@Component
interface AppComponent {
    fun marsPhotosViewModelFactory():MarsPhotosViewModelFactory
}