package com.example.dickiez.browser;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private WebView webv;
    private EditText txturl;
    private Button btncari;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webv  = (WebView)findViewById(R.id.webv);
        txturl = (EditText)findViewById(R.id.txturl);
        btncari = (Button)findViewById(R.id.btncari);

        String url = "https://www.google.co.id";

        webv.getSettings().setJavaScriptEnabled(true);
        webv.getSettings().setDisplayZoomControls(true);
        webv.getSettings().setLoadWithOverviewMode(true);
        webv.getSettings().setUseWideViewPort(true);
        webv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webv.getSettings().setAllowFileAccessFromFileURLs(true);
        webv.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webv.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        pb = (ProgressBar)findViewById(R.id.progressbar);
        webv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pb.setVisibility(View.VISIBLE);
                pb.setProgress(newProgress);
                if(newProgress == 100) {
                    pb.setVisibility(View.GONE);
                }
            }
        });

        webv.loadUrl(url);
        webv.setWebViewClient(new MyWebLaunch());
        btncari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://"+txturl.getText().toString();

                webv.getSettings().setJavaScriptEnabled(true);
                webv.getSettings().setDisplayZoomControls(true);

                pb = (ProgressBar)findViewById(R.id.progressbar);
                webv.setWebChromeClient(new WebChromeClient() {
                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        pb.setVisibility(View.VISIBLE);
                        pb.setProgress(newProgress);
                        if(newProgress == 100) {
                            pb.setVisibility(View.GONE);
                        }
                    }
                });
                webv.loadUrl(url);
                webv.setWebViewClient(new MyWebLaunch());
            }
        });
    }

    private class MyWebLaunch extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK :
                    if (webv.canGoBack()) {
                        webv.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
