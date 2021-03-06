package com.apkfuns.weeksample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;
import com.taobao.weex.utils.WXViewUtils;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;

    private static final String DEFAULT_IP = "172.24.86.73";
    private static String CURRENT_IP= DEFAULT_IP; // your_current_IP
    private static final String WEEX_INDEX_URL = "http://"+CURRENT_IP+":12580/examples/build/index.js";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        frameLayout = (FrameLayout) findViewById(R.id.content);
        WXSDKInstance mInstance = new WXSDKInstance(this);
        mInstance.registerRenderListener(new IWXRenderListener() {
            @Override
            public void onViewCreated(WXSDKInstance instance, View view) {
                frameLayout.addView(view);
            }

            @Override
            public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

            }

            @Override
            public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

            }

            @Override
            public void onException(WXSDKInstance instance, String errCode, String msg) {

            }
        });
        renderPage(mInstance,getPackageName(), WXFileUtils.loadFileContent("hello.js",this), WEEX_INDEX_URL ,null);
    }

    protected void renderPage(WXSDKInstance mInstance, String packageName, String template, String source, String jsonInitData){
        Map<String, Object> options = new HashMap<>();
        options.put(WXSDKInstance.BUNDLE_URL, source);
        mInstance.render(
                packageName,
                template,
                options,
                jsonInitData,
                WXViewUtils.getScreenWidth(this),
                WXViewUtils.getScreenHeight(this),
                WXRenderStrategy.APPEND_ASYNC);
    }
}
