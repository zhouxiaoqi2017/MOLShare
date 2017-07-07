package com.ricky.f.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.ricky.f.ui.login.LoginActivity;

/**
 * Created by Deak on 17/5/4.
 */

public interface BaseView {
    Context getContext();

    void showToast(String toast);

    void showLoadingDialog(String msg);
    void dismissLoadingDialog();

    void openActivity(String actUrl, int requestCode);
}
