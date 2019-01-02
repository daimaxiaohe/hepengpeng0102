package com.example.hepengpeng0102.contract;

import com.example.hepengpeng0102.net.ResponceCallBack;

import java.util.HashMap;

/**
 * はすてすゃの
 * 2019-01-02.
 */
public interface ProductContract {
    //定义一个抽象类
    public abstract class CProductPresenter{
        public abstract void showProduct(String uri, HashMap<String,String> map, Class cc);
    }
    //定一个model层接口
    public interface IProductModel{
        public void showSuccess(String uri, HashMap<String,String> map, Class cc, ResponceCallBack callBack);
    }
    //定义一个view层的接口
    public interface IProductView{
        public void success(Object object);
        public void fail(String string);
    }
}
