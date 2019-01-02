package com.example.hepengpeng0102.utils;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * はすてすゃの
 * 2019-01-02.
 */
public class OkHttpUtil {
    OkHttpClient okHttpClient;
    //创建一个单例模式
    private static OkHttpUtil minstance;
    //私有的构造方法
    private OkHttpUtil(){
        //日志；拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        //okhttp管理类
        okHttpClient = new OkHttpClient.Builder()
                //添加日志拦截器
                .addInterceptor(interceptor)
                .addNetworkInterceptor(interceptor)
                //设置超时时间
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();
    }
    //单例方法
    public static OkHttpUtil getMinstance(){
        if (minstance==null){
            synchronized (OkHttpUtil.class){
                if (minstance==null){
                    minstance = new OkHttpUtil();
                }
            }
        }
        return minstance;
    }
    //post网络请求的方法
    public void getnet(String uri, HashMap<String,String> map, final Class cc, final OkHTTPCallBack callBack){
        //post请求
        //请求体的数据
        FormBody.Builder builder = new FormBody.Builder();
        if (map!=null) {
            for (Map.Entry<String, String> p : map.entrySet()) {
                builder.add(p.getKey(), p.getValue());
            }
        }
        //request请求
        Request request = new Request.Builder().url(uri).post(builder.build()).build();
        //call
        okHttpClient.newCall(request).enqueue(new Callback() {
            //失败的方法
            @Override
            public void onFailure(Call call, IOException e) {
                if (callBack!=null){
                    callBack.fail("网络数据请求失败");
                }
            }
            //成功的方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    if (callBack!=null){
                        if (200==response.code()){
                            getjson(response.body().string(),cc,callBack);
                        }
                    }
            }
        });
    }
    //json解析的方法
    private void getjson(String result, Class cc, OkHTTPCallBack callBack){
        //开始jsOn解析
        Object o = new Gson().fromJson(result, cc);
        if (o!=null){
            callBack.success(o);
        }else{
            callBack.fail("解析数据失败");
        }
    }
    //关闭Okhttp请求的方法
    public void okhttpcancel(){
        if (okHttpClient!=null){
            okHttpClient.dispatcher().cancelAll();
        }
    }

}
