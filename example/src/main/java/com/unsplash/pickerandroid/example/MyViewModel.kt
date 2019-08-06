package com.unsplash.pickerandroid.example

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unsplash.pickerandroid.photopicker.data.UnsplashPhoto

class MyViewModel : ViewModel(){
    var photos: ArrayList<UnsplashPhoto>? = null
}