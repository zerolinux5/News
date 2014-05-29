package com.zerolinux5.newsview;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends ActionBarActivity {
	String url;
	WebView web;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);
		Intent intent = getIntent();
		url = intent.getStringExtra("Url");
		web = (WebView) findViewById(R.id.webView1);
		WebSettings webSettings = web.getSettings();
		webSettings.setJavaScriptEnabled(true);
		web.setWebViewClient(new WebViewClient());
		web.loadUrl(url);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.web, menu);
		return true;
	}
	
	@Override
	public void onPause(){
		Method pause = null;
    	// Resumes the webview.
    	try {
    		pause = WebView.class.getMethod("onPause");
    	} catch (SecurityException e) {
    		// Nothing
    	} catch (NoSuchMethodException e) {
    		// Nothing
    	}	if (pause != null) {
    		try {
    			pause.invoke(web);
    		} catch (InvocationTargetException e) {
			} catch (IllegalAccessException e) {
			}
    	} else {
    		// And loads a URL without any processing.
    		web.loadUrl("");
    	}
		super.onPause();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if((keyCode == KeyEvent.KEYCODE_BACK)&& web.canGoBack()) {
			web.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
