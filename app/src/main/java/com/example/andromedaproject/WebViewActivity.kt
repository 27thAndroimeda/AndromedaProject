package com.example.andromedaproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        setView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun setView() {
        val url = intent.getStringExtra("web_url")
        webview_kakao_web.settings.javaScriptEnabled = true

        webview_kakao_web.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url!!)
                return true
            }
        }
        webview_kakao_web.loadUrl(url!!)
    }
}