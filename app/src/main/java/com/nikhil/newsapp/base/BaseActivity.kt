package com.nikhil.newsapp.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    abstract val viewModel: VM

    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as B
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel(viewModel)

    }

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun initViewModel(viewModel: VM)
}
