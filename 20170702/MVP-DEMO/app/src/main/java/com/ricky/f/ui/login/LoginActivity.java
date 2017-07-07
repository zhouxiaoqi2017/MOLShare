package com.ricky.f.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ricky.f.R;
import com.ricky.f.base.BaseActivity;

public class LoginActivity extends BaseActivity<LoginContract.View, LoginContract.Presenter> implements LoginContract.View {

    private LoginPresenter mLoginPresenter = new LoginPresenter();

    private TextView mTvUserInfo;
    private TextView mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        mTvUserInfo = (TextView) findViewById(R.id.tv_userInfo);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    public void login(View v){
        mLoginPresenter.login("xx", "xxx");
    }

    @Override
    protected LoginContract.Presenter getPresenter() {
        return mLoginPresenter;
    }

    @Override
    public void loginSuccess() {
        showToast("登录成功");
        mTvUserInfo.setText(mLoginPresenter.user.toString());
        mBtnLogin.setVisibility(View.GONE);
    }
}
