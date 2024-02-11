package com.example.chittopot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class web extends AppCompatActivity {
    WebView webView;
    String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Intent intent = getIntent();
        if (intent != null) {
            userEmail = intent.getStringExtra("kkk");
            Log.d("PreviousHistoryActivity", "User Email: " + userEmail);
        }

        webView = findViewById(R.id.web);
        ProgressDialog progressDialog = ProgressDialog.show(web.this,"Loading","Please Wait",true);
        progressDialog.setCancelable(false);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progressDialog.show();
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressDialog.dismiss();
            }
        });


        if (userEmail == "Happy"){
            webView.loadUrl("https://www.youtube.com/results?search_query=funny+videos");
        }
        else if(userEmail == "Sad"){
            webView.loadUrl("https://www.youtube.com/results?search_query=motivational+video");
        }
        else if (userEmail == "Love"){
             webView.loadUrl("https://www.youtube.com/results?search_query=upbeat+songs");

        }
        else{
            webView.loadUrl("https://www.youtube.com/results?search_query=mind+calm+videos");

        }

    }
}