package com.ricky.f.manager;

import com.ricky.f.config.AppConfig;
import com.ricky.f.listener.CallbackListener;
import com.ricky.f.util.HttpUtils;

/**
 * Created by Deak on 17/5/4.
 */

public class UserManager {

    public static final String Login = "Login";
    public static final String Register = "Register";

    public void login(String name, String password, CallbackListener callbackListener) {
        HttpUtils.getInstance().send(HttpUtils.HttpMethod.GET, Login, AppConfig.BASE_URL, callbackListener);
    }
}
