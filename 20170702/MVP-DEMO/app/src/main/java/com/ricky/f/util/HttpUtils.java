package com.ricky.f.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apkfuns.logutils.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.ricky.f.bean.NetBean;
import com.ricky.f.listener.CallbackListener;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Deak on 16/10/11.
 */

public class HttpUtils {

    public enum HttpMethod{
        GET, POST, PUT, DELETE
    }

    private static class SingletonHolder {
        private static final HttpUtils INSTANCE = new HttpUtils();
    }

    public static HttpUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void send(HttpMethod method, Object tag, String url, CallbackListener callbackListener){
        send(method, tag, url, null, callbackListener);

    }

    public void send(HttpMethod method, Object tag, String url, Map<String, Object> parameters, CallbackListener callbackListener){
        if(method == HttpMethod.GET){
            get(tag, url, parameters, callbackListener);
            return;
        }
        if(method == HttpMethod.POST){
            post(tag, url, parameters, callbackListener);
            return;
        }
        if(method == HttpMethod.PUT){
            put(tag, url, parameters, callbackListener);
            return;
        }
        if(method == HttpMethod.DELETE){
            delete(tag, url, parameters, callbackListener);
            return;
        }
    }

    private void get(Object tag, String url, Map<String, Object> parameters, CallbackListener callbackListener) {
        OkGo.get(url)
                .tag(tag)
                .params(formatParamters(parameters))
                .execute(new MyStringCallback(callbackListener));
    }

    private void post(Object tag, String url, Map<String, Object> parameters, CallbackListener callbackListener) {
        OkGo.post(url)
                .tag(tag)
                .params(formatParamters(parameters))
                .execute(new MyStringCallback(callbackListener));
    }

    private void put(Object tag, String url, Map<String, Object> parameters, CallbackListener callbackListener) {
        OkGo.put(url)
                .tag(tag)
                .params(formatParamters(parameters))
                .execute(new MyStringCallback(callbackListener));
    }

    private void delete(Object tag, String url, Map<String, Object> parameters, CallbackListener callbackListener) {
        OkGo.delete(url)
                .tag(tag)
                .params(formatParamters(parameters))
                .execute(new MyStringCallback(callbackListener));
    }

    class MyStringCallback extends StringCallback{

        private CallbackListener callbackListener;

        MyStringCallback(CallbackListener callbackListener){
            this.callbackListener = callbackListener;
        }

        @Override
        public void onSuccess(String s, Call call, Response response) {
            String tag = response.request().tag().toString();
            try {
//                JSONObject jsonObject = JSON.parseObject(response.body().string());
//                LogUtils.i("http response data:"+ jsonObject.toJSONString());
//                int code = jsonObject.getIntValue("code");
//                String message = jsonObject.getString("message");
//                String data = jsonObject.getString("data");

                int code = 1001;
                String message = "登录成功";
                String data = "{\"identity\": \"00000001\",\"account\": \"ricky\",\"type\": 1,\"gender\": 1,\"nickname\": \"ricky\",\"avatar\": \"http://xxxxx.png\",\"token\": \"k9fsdskerwewrrew\",\"expireDate\": \"10003433333\"}";

                //RxBusUtils.getInstance().post(new NetBean(tag, code, message, data));
                callbackListener.onResult(new NetBean(tag, code, message, data));
            } catch (Exception e) {
                e.printStackTrace();
                callbackListener.onResult(new NetBean(tag));
            }
        }

        @Override
        public void onError(Call call, Response response, Exception e) {
            super.onError(call, response, e);
            String tag = response.request().tag().toString();
            callbackListener.onResult(new NetBean(tag, response.code(), response.message(), ""));
        }
    }

    private HttpParams formatParamters(Map<String, Object> paramters){
        HttpParams p = new HttpParams();
        if(paramters != null && paramters.size() > 0){
            for(String key : paramters.keySet()){
                Object value = paramters.get(key);
                if(value instanceof String){
                    p.put(key, (String)value);
                } else if(value instanceof Integer){
                    p.put(key, (Integer)value);
                } else if(value instanceof Float){
                    p.put(key, (Float)value);
                } else if(value instanceof Double){
                    p.put(key, (Double)value);
                } else if(value instanceof Boolean){
                    p.put(key, (Boolean)value);
                }
            }
        }
        return p;
    }
}
