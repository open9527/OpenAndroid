package com.farmer.open9527.module.test.network;

import androidx.annotation.NonNull;

import com.farmer.open9527.base.BaseActivity;
import com.farmer.open9527.base.page.DataBindingConfig;

/**
 * @author open_9527
 * Create at 2021/10/19
 **/
class TestOneActivity extends BaseActivity {
    @Override
    protected void initViewModel() {
            getActivityScopeViewModel(TestOneViewModel.class);
    }

    @NonNull
    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return null;
    }
}
