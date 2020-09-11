package com.nikhil.newsapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nikhil.newsapp.R
import com.nikhil.newsapp.ui.TechNewsActivity
import com.nikhil.newsapp.ui.bitcoin.BitcoinActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_tech_news.setOnClickListener {
            startActivity(Intent(this, TechNewsActivity::class.java))
        }
        tv_bitcoin_news.setOnClickListener {
            startActivity(Intent(this, BitcoinActivity::class.java))
        }
    }
}