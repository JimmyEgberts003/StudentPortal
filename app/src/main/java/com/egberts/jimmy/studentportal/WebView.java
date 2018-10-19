package com.egberts.jimmy.studentportal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebViewClient;

public class WebView extends AppCompatActivity {
    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        android.webkit.WebView myWebView = findViewById(R.id.webView);

        //Get the url that is needed for loading the webview
        mUrl = getIntent().getStringExtra(MainActivity.URL_CODE);

        myWebView.setWebViewClient(new WebViewClient());

        //pass the url to the webview
        myWebView.loadUrl(mUrl);
    }
}
