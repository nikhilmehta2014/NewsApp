package com.nikhil.newsapp.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * This functions helps in transforming a [MutableLiveData] of type [T]
 * to a [LiveData] of type [T]
 */
fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>