package com.butter.yapplet.webview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by 16838 on 2017/12/26.
 */
public class SetWebViewTools {
    /**
     * 加载webView的方法
     */
    public static void setWebView(String url, final WebView webview, final Context context) {
        //      对webView的设置
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
//      有些网页不能显示js请求的数据时要加这句话，作用是能够把信息存储到本地的计算机上，在需要时获取这点和 cookie 相似
        webSettings.setDomStorageEnabled(true);

        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

            }
        });

        /**
         * 拿到当前页面的url
         */
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("111", "url>>>" + url);
//              网页中有微信支付时，调不起来，这里要做判断
                if (url.startsWith("weixin://wap/pay?")) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    context.startActivity(intent);

                    return true;
                }
                if (url.equals("http://static.tubeipay.com/static/pay_success.html")) {
                    //              解决跳转之后的白色页面
                    webview.loadUrl("https://4028899.com/h5/#/userCenter/register?code=152929");
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
        webview.loadUrl(url);

    }
}
