package com.nikhil.newsapp.ui.bitcoin

import androidx.hilt.lifecycle.ViewModelInject
import com.nikhil.newsapp.base.BaseViewModel
import com.nikhil.newsapp.source.repository.SavedNewsRepository

class BitcoinViewModel @ViewModelInject constructor(
    private val bitcoinNewsRepository: BitcoinNewsRepository
){

}