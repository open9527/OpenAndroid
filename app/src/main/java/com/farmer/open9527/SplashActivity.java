package com.farmer.open9527;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ActivityUtils;
import com.farmer.open9527.module.test.media.MediaActivity;
import com.farmer.open9527.module.test.network.NetWorkActivity;

/**
 * @author open_9527
 * Create at 2021/10/20
 **/
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.splash_activity);
//        ActivityUtils.startActivity(NetWorkActivity.class);
        ActivityUtils.startActivity(MediaActivity.class);
        finish();
    }

    @Override
    public void onBackPressed() {

    }
}
