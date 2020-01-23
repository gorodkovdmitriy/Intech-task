package com.intech.task.binding_adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.intech.task.R
import com.squareup.picasso.Picasso

@BindingAdapter("app:load_from_url")
fun ImageView.loadFromUrl(url: String) {
    Picasso.get().load(url).placeholder(R.drawable.album_placeholder).into(this)
}