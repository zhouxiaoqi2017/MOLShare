package com.ricky.f.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.ricky.f.util.AppUtils;
import com.ricky.f.util.DialogUtils;
import com.ricky.f.util.ToastUtils;
import com.ricky.f.util.UrlRouterUtils;

/**
 * Created by Deak on 16/10/11.
 */

public abstract class BaseActivity<V,T extends BasePresenter<V>> extends AppCompatActivity {

    protected Context mContext;

    public T mPresenter;

    protected LayoutInflater mLayoutInflater;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter=getPresenter();
        //关联view
        mPresenter.attachView((V)this);

        mContext = this;

        mLayoutInflater = getLayoutInflater();
        View view = mLayoutInflater.inflate(getLayoutId(), null);
        setContentView(view);
    }

    public Context getContext(){
        return this;
    }

    /**
     * get content view layout id
     *
     * @return
     */
    public abstract int getLayoutId();

    //具体的presenter由子类返回
    protected abstract T getPresenter() ;

    public void showToast(String toast) {
        ToastUtils.showToast(this, toast);
    }
    public void showLoadingDialog(String msg) {
        DialogUtils.showLoadingDialog(this, msg);
    }
    public void dismissLoadingDialog() {
        DialogUtils.dismissLoadingDialog();
    }
    public void openActivity(String actUrl, int requestCode) {
        AppUtils.getInstance().startActivity(this, actUrl, requestCode, false, false, false, true, 0, 0, new Object[]{});
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解关联view
        mPresenter.detachView();
    }
}
