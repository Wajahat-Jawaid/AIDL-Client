package com.wajahat.golootloandroidtest.binding

import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.wajahat.golootloandroidtest.util.DisplayUtils

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("layout_height")
fun setPhotosListItemHeight(view: ImageView, height: Float) {
    val layoutParams: ViewGroup.LayoutParams = view.layoutParams as ViewGroup.LayoutParams
    // Setting image height to be 40% of the screen height
    layoutParams.height = (DisplayUtils.getScreenHeight().toFloat() * 0.4).toInt()
    view.layoutParams = layoutParams
}