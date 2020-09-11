package com.nikhil.newsapp.ui.bitcoin

import android.os.Bundle
import androidx.activity.viewModels
import com.nikhil.newsapp.R
import com.nikhil.newsapp.base.BaseActivity
import com.nikhil.newsapp.databinding.ActivityBitcoinNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BitcoinActivity:BaseActivity<ActivityBitcoinNewsBinding, BitcoinViewModel>() {

    override val viewModel: BitcoinViewModel by viewModels()

    override fun getLayoutRes() = R.layout.activity_bitcoin_news

    override fun initViewModel(viewModel: BitcoinViewModel) {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = this.getString(R.string.bitcoin_news)
    }

}