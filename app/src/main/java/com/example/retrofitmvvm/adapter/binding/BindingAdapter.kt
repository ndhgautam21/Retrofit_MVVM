package com.example.retrofitmvvm.adapter.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitmvvm.listener.UserListener
import com.example.retrofitmvvm.models.Users

@BindingAdapter(value = ["android:loadImage"])
fun ImageView.imageFromURL(url : String) {
    Glide.with(this.context).load(url).into(this)
}

@BindingAdapter(value = ["android:adapter", "android:context", "android:layoutManager"])
fun RecyclerView.setAdapters(list: List<Users>, listener: UserListener, layoutManager: LinearLayoutManager) {

}
