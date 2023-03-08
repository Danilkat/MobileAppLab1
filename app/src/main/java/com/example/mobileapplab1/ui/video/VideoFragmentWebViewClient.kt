package com.example.mobileapplab1.ui.video

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import java.net.URL

class VideoFragmentWebViewClient: WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val url = request?.url
        val host: String? = url?.host
        return "learnenglish.britishcouncil.org" != host
    }
}