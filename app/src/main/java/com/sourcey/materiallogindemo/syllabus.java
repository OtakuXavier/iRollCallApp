package com.sourcey.materiallogindemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class syllabus extends AppCompatActivity {

    WebView mWebView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syllabus);
        mWebView = (WebView)findViewById(R.id.webview);
        mWebView.loadUrl("http://tw.yahoo.com");
        mWebView.setWebViewClient(new WebViewClient()); //disable address bar
    }
}