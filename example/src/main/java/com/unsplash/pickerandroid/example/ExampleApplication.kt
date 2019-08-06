package com.unsplash.pickerandroid.example

import android.app.Application
import com.unsplash.pickerandroid.photopicker.UnsplashPhotoPicker

class ExampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // initializing the picker library
        UnsplashPhotoPicker.init(
            this,
            "8e690d01dce5f579908ddb5a8133e1bf6ac9ca06035a24d000e959be4b23a98e",
            "8a308de4cb4e3e8cde5e6b0f286b513dd5f6ceeb3c1a2cddf88e0fc88446c84b"
            /* optional page size (number of photos per page) */
        )
            /* .setLoggingEnabled(true) // if you want to see the http requests */
    }
}
