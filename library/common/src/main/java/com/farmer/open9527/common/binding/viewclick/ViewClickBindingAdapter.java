package com.farmer.open9527.common.binding.viewclick;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.databinding.BindingAdapter;

import com.blankj.utilcode.util.ClickUtils;


/**
 * @author open_9527
 * Create at 2021/1/6
 **/
public class ViewClickBindingAdapter {

    /**
     * 配置view的点击事件
     *
     * @param view            view
     * @param debounce        默认处理防抖
     * @param viewScale       应用点击后对视图缩放
     * @param viewAlpha       应用点击后对视图改变透明度
     * @param onClickListener onClickListener
     */
    @SuppressLint("CheckResult")
    @BindingAdapter(value = {
            "bindViewClick",
            "bindViewClickDebounce",
            "bindViewClickViewScale",
            "bindViewClickViewAlpha"
    },
            requireAll = false)
    public static void setBindingViewClick(View view, View.OnClickListener onClickListener,
                                           boolean debounce,
                                           boolean viewScale,
                                           boolean viewAlpha
    ) {
        if (view == null || onClickListener == null) {
            return;
        }
        if (viewScale) ClickUtils.applyPressedViewScale(view);
        if (viewAlpha) ClickUtils.applyPressedViewAlpha(view);
        if (debounce) {
            view.setOnClickListener(onClickListener);
        } else {
            ClickUtils.applyGlobalDebouncing(view, onClickListener);
        }
    }

    /**
     * 配置view的长按事件
     *
     * @param view                view
     * @param viewScale           应用点击后对视图缩放
     * @param viewAlpha           应用点击后对视图改变透明度
     * @param onLongClickListener onLongClickListener
     */
    @SuppressLint("CheckResult")
    @BindingAdapter(value = {
            "bindViewLongClick",
            "bindViewLongClickViewScale",
            "bindViewLongClickViewAlpha",},
            requireAll = false)
    public static void setBindingViewLongClick(View view, View.OnLongClickListener onLongClickListener,
                                               boolean viewScale,
                                               boolean viewAlpha
    ) {
        if (view == null) return;
        if (onLongClickListener == null) return;
        if (viewScale) ClickUtils.applyPressedViewScale(view);
        if (viewAlpha) ClickUtils.applyPressedViewAlpha(view);
        view.setOnLongClickListener(onLongClickListener);

    }
}
