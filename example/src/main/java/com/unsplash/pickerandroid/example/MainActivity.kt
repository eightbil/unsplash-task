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
        // 화면회전 했을 때 사진이 사라지는 현상방지 위해 뷰모델 사용
        model = ViewModelProviders.of(this)[MyViewModel::class.java]

        //리싸이클러뷰 이니셜
        main_recycler_view.setHasFixedSize(true)
        main_recycler_view.itemAnimator = null
        main_recycler_view.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        mAdapter = MyAdapter(this)
        main_recycler_view.adapter = mAdapter
        main_pick_button.setOnClickListener {
            //한장선택 라디오버튼이 선택되었는지를 체크하여 인텐트에 포함, 액티비티를 실행
            startActivityForResult(
                UnsplashPickerActivity.getStartingIntent(
                    this,
                    !main_single_radio_button.isChecked
                ), Companion.REQUEST_CODE
            )
        }

        // 화면회전되면 onCreate가 재실행되고 모델뷰에 유지되고 있는 사진들을 다시 셋해줌
        if(model.photos != null) {
            mAdapter.setListOfPhotos(model.photos)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == Companion.REQUEST_CODE) {
            // UnsplashPickerActivity에서 보낸 데이터를 받아서 리싸이클러뷰 어뎁터에 넣어줌
            model.photos = data?.getParcelableArrayListExtra(UnsplashPickerActivity.EXTRA_PHOTOS)
            mAdapter.setListOfPhotos(model.photos)
            Toast.makeText(this, "선택된 사진 수 : " + model.photos?.size, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val REQUEST_CODE = 1
    }
}
