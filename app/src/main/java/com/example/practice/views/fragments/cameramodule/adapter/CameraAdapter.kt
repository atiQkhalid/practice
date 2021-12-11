package com.example.practice.views.fragments.cameramodule.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.R

class CameraAdapter(private val images: MutableList<Uri>) : RecyclerView.Adapter<CameraAdapter.MyViewHolder>() {

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var ivPictures = v.findViewById(R.id.iv_clickedPicture) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_image, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val image = images[position]
        holder.ivPictures.setImageURI(image)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}