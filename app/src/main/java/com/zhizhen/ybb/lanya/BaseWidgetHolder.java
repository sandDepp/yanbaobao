package com.zhizhen.ybb.lanya;

import android.view.View;

/**
 * 自绘控件封装类
 * Created by vonchenchen on 2015/11/3 0003.
 */
public abstract class BaseWidgetHolder<T> {

    protected View mRootView;

    public abstract View initView();
    public abstract void refreshView(T data);

    public BaseWidgetHolder(){
        mRootView = initView();
        mRootView.setTag(this);
    }

    public View getRootView(){
        return mRootView;
    }
}
