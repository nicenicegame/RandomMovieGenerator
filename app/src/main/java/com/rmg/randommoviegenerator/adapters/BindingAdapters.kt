package com.rmg.randommoviegenerator.adapters

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter("imageUrl", "imageSize")
fun ImageView.bindImage(imageUrl: String?, imageSize: Int) {
    imageUrl?.let {
        val url = Uri.parse(Constant.IMAGE_URL)
            .buildUpon()
            .appendPath("w$imageSize")
            .appendPath(imageUrl)
            .build()
        Glide.with(this).load(url).into(this)
    }
}

@BindingAdapter("isGone")
fun View.bindIsGone(boolean: Boolean) {
    this.visibility = if (boolean) View.GONE else View.VISIBLE
}

@BindingAdapter("isFabGone")
fun FloatingActionButton.bindIsFabGone(boolean: Boolean) {
    if (boolean) this.hide()
    else this.show()
}

object Constant {
    const val IMAGE_URL = "https://image.tmdb.org/t/p/"
}