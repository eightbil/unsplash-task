package com.unsplash.pickerandroid.example

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.unsplash.pickerandroid.photopicker.data.UnsplashPhoto
import com.unsplash.pickerandroid.photopicker.presentation.UnsplashPickerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: PhotoAdapter
    private lateinit var model : MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = ViewModelProviders.of(this)[MyViewModel::class.java]

        // result adapter
        // recycler view configuration
        main_recycler_view.setHasFixedSize(true)
        main_recycler_view.itemAnimator = null
        main_recycler_view.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        mAdapter = PhotoAdapter(this)
        main_recycler_view.adapter = mAdapter
        // on the pick button click, we start the library picker activity
        // we are expecting a result from it so we start it for result
        main_pick_button.setOnClickListener {
            startActivityForResult(
                UnsplashPickerActivity.getStartingIntent(
                    this,
                    !main_single_radio_button.isChecked
                ), REQUEST_CODE
            )
        }

        if(model.photos != null) {
            mAdapter.setListOfPhotos(model.photos)
        }

        Toast.makeText(this, "onCreate 실행" , Toast.LENGTH_SHORT).show()

    }

    // here we are receiving the result from the picker activity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            // getting the photos
            //val photos: ArrayList<UnsplashPhoto>? = data?.getParcelableArrayListExtra(UnsplashPickerActivity.EXTRA_PHOTOS)
            model.photos = data?.getParcelableArrayListExtra(UnsplashPickerActivity.EXTRA_PHOTOS)
            // showing the preview
           // mAdapter.setListOfPhotos(photos)
            mAdapter.setListOfPhotos(model.photos)
            // telling the user how many have been selected
            Toast.makeText(this, "선택된 사진 수 : " + model.photos?.size, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        // dummy request code to identify the request
        private const val REQUEST_CODE = 123
    }
}
