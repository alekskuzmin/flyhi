package com.alekskuzmin.flyhi.core.view

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:visible")
fun setVisibility(view: View, visible: Boolean?) {
    view.visibility = if (visible == true) View.VISIBLE else View.GONE
}

@BindingAdapter("app:imageFile")
fun loadImageFileFromUri(view: ImageView, imagePath: String?) {
    if (imagePath == null) return
    val activity = view.context

    GlideApp
        .with(activity)
        .load(imagePath)
        .into(view)
}