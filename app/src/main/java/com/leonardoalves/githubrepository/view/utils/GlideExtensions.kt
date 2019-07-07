package com.leonardoalves.githubrepository.view.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

fun <T> RequestBuilder<T>.circleCropBuilder(): RequestBuilder<T> {
    return apply(RequestOptions().circleCrop())
}

fun <T> RequestBuilder<T>.centerCropBuilder(): RequestBuilder<T> {
    return apply(RequestOptions().centerCrop())
}

fun RequestBuilder<Drawable>.crossFade(): RequestBuilder<Drawable> {
    return transition(DrawableTransitionOptions.withCrossFade())
}

fun RequestBuilder<Bitmap>.bitmapCrossFade(): RequestBuilder<Bitmap> {
    return transition(BitmapTransitionOptions.withCrossFade())
}