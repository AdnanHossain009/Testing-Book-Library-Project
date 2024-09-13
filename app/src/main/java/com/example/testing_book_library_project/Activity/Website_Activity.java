package com.example.testing_book_library_project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.testing_book_library_project.R;

public class Website_Activity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_website);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        if(null != intent){
            String url = intent.getStringExtra("url");

            webView = findViewById(R.id.webView);

            webView.loadUrl("https://www.google.com/");         //it will open the google browser on defauly browser chrome
            webView.setWebViewClient(new WebViewClient());      //it will open the browser inside the application

            webView.getSettings().setJavaScriptEnabled(true);   //setting the javascript enable is necessary.

        }

    }

    @Override
    public void onBackPressed() {

        if(webView.canGoBack())
            webView.goBack();

        else{
            super.onBackPressed();
        }

    }
}