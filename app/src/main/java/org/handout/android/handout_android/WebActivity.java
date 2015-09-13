package org.handout.android.handout_android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class WebActivity extends Activity {

    private WebView wv_webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        String url = getIntent().getExtras().getString("URL");
        Log.d("TAG", url);

        wv_webView = (WebView) findViewById(R.id.webview_WebView);

        wv_webView.getSettings().setJavaScriptEnabled(true);
//        wv_webView.getSettings().setPluginsEnabled(true);

        wv_webView.loadUrl(url);
    }

}
