package com.unsplash.pickerandroid.example

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.unsplash.pickerandroid.photopicker.data.UnsplashPhoto
import kotlinx.android.synthetic.main.item_photo.view.*

class MyAdapter constructor(context: Context) : RecyclerView.Adapter<MyAdapter.PhotoViewHolder>() {

    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(context)

    private var mListOfPhotos: List<UnsplashPhoto> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(mLayoutInflater.inflate(R.layout.item_photo, parent, false))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {

        val photo = mListOfPhotos[position]

        holder.itemView.setBackgroundColor(Color.parseColor(photo.color))

        Picasso.get().load(photo.urls.small)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return mListOfPhotos.size
    }




    fun setListOfPhotos(listOfPhotos: ArrayList<UnsplashPhoto>?) {
        if (listOfPhotos != null) {
            mListOfPhotos = listOfPhotos
            notifyDataSetChanged()
        }
    }

    class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.item_photo_iv
    }
}