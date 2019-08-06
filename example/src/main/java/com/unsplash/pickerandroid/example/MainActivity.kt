package com.unsplash.pickerandroid.example

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.unsplash.pickerandroid.photopicker.presentation.UnsplashPickerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: MyAdapter
    private lateinit var model : MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        Toast.makeText(this, "onCreate 실행" , Toast.LENGTH_SHORT).show()
    }

    fun init(){
        model = ViewModelProviders.of(this)[MyViewModel::class.java]

        main_recycler_view.setHasFixedSize(true)
        main_recycler_view.itemAnimator = null
        main_recycler_view.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        mAdapter = MyAdapter(this)
        main_recycler_view.adapter = mAdapter
        main_pick_button.setOnClickListener {
            startActivityForResult(
                UnsplashPickerActivity.getStartingIntent(
                    this,
                    !main_single_radio_button.isChecked
                ), Companion.REQUEST_CODE
            )
        }

        if(model.photos != null) {
            mAdapter.setListOfPhotos(model.photos)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == Companion.REQUEST_CODE) {
            model.photos = data?.getParcelableArrayListExtra(UnsplashPickerActivity.EXTRA_PHOTOS)
            mAdapter.setListOfPhotos(model.photos)
            Toast.makeText(this, "선택된 사진 수 : " + model.photos?.size, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val REQUEST_CODE = 1
    }
}
