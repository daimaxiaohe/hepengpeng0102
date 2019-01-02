package com.example.hepengpeng0102.presenter;

import com.example.hepengpeng0102.contract.ProductContract;
import com.example.hepengpeng0102.model.ProductModel;
import com.example.hepengpeng0102.net.ResponceCallBack;
import com.example.hepengpeng0102.utils.OkHttpUtil;

import java.util.HashMap;

/**
 * はすてすゃの
 * 2019-01-02.
 */
public class ProductPresenter extends ProductContract.CProductPresenter {
    private ProductModel model;
    private ProductContract.IProductView view;
    public ProductPresenter(ProductContract.IProductView view) {
        model = new ProductModel();
        this.view = view;
    }

    @Override
    public void showProduct(String uri, HashMap<String,String> map, Class cc) {
        if (model!=null){
            model.showSuccess(uri, map, cc, new ResponceCallBack() {
                @Override
                public void success(Object o) {
                    if (view!=null){
                        view.success(o);
                    }
                }

                @Override
                public void fail(String string) {
                    if (view!=null){
                        view.fail(string);
                    }
                }
            });
        }
    }
    //防止内存泄漏
    public void setcache(){
        if (view!=null){
            view=null;
            OkHttpUtil.getMinstance().okhttpcancel();
        }
    }
}
