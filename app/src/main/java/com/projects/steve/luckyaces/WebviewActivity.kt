package com.projects.steve.luckyaces

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView
import android.webkit.WebViewClient

import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity : AppCompatActivity() {

//    var webview: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        var webview = findViewById<WebView>(R.id.webview) as? WebView
        webview!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }

        webview!!.loadUrl("http://www.johnnyaces.com")

    }
}
