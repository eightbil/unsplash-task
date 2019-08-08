package com.unsplash.pickerandroid.example

import androidx.lifecycle.ViewModel
import com.unsplash.pickerandroid.photopicker.data.UnsplashPhoto

class MyViewModel : ViewModel(){
     // 화면회전 시 사진사라짐 현상 방지위해 뷰모델에 리스트변수를 지정
     var photos: ArrayList<UnsplashPhoto>? = null
}