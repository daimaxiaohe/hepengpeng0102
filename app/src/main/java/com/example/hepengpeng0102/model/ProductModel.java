package com.example.hepengpeng0102.model;

import android.os.Handler;

import com.example.hepengpeng0102.contract.ProductContract;
import com.example.hepengpeng0102.net.ResponceCallBack;
import com.example.hepengpeng0102.utils.OkHTTPCallBack;
import com.example.hepengpeng0102.utils.OkHttpUtil;

import java.util.HashMap;

/**
 * はすてすゃの
 * 2019-01-02.
 */
public class ProductModel implements ProductContract.IProductModel {
    //创建一个Handler
    Handler handler = new Handler();
    @Override
    public void showSuccess(String uri, HashMap<String,String> map, Class cc, final ResponceCallBack callBack) {
        OkHttpUtil.getMinstance().getnet(uri, map, cc, new OkHTTPCallBack() {
            @Override
            public void success(final Object object) {
                if (callBack!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.success(object);
                        }
                    });
                }
            }

            @Override
            public void fail(final String string) {
                if (callBack!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.fail(string);
                        }
                    });
                }
            }
        });
    }
}
