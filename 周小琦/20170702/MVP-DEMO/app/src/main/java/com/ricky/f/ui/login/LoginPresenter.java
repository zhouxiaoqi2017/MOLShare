package com.ricky.f.ui.login;

import com.android.utils.FastJsonUtil;
import com.ricky.f.bean.NetBean;
import com.ricky.f.bean.User;
import com.ricky.f.listener.CallbackListener;
import com.ricky.f.manager.UserManager;

/**
 * Created by Deak on 17/5/3.
 */

public class LoginPresenter extends LoginContract.Presenter {

    UserManager userManager = new UserManager();

    public User user;

    @Override
    public void login(String name, String password) {
        mView.showLoadingDialog("请稍候...");
        userManager.login("ricky", "111", callbackListener);
    }

    @Override
    protected void success(NetBean bean) {
        if(UserManager.Login.equals(bean.getTag())){
            mView.dismissLoadingDialog();
            user = FastJsonUtil.getBean(bean.getData(), User.class);
            mView.loginSuccess();
            return;
        }
        if(UserManager.Register.equals(bean.getTag())){

            return;
        }
    }

    @Override
    protected void failure(String tag, int errCode, String message) {
        super.failure(tag, errCode, message);
        mView.showToast(tag + " " + message);
    }
}
