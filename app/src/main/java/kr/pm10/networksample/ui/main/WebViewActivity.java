package kr.pm10.networksample.ui.main;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import kr.pm10.networksample.R;
import kr.pm10.networksample.library.Constant;
import kr.pm10.networksample.ui.base.BaseActivity;

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.web_view)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        String gitHubUrl = getIntent().getStringExtra(Constant.INTENT_KEY_URL);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClientClass());
        webView.loadUrl(gitHubUrl);
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            loadingStart();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            loadingFinish();
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }
}
