package com.example.hepengpeng0102.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hepengpeng0102.R;
import com.example.hepengpeng0102.api.HomeApi;
import com.example.hepengpeng0102.bean.ProductBean;
import com.example.hepengpeng0102.contract.ProductContract;
import com.example.hepengpeng0102.presenter.ProductPresenter;

import java.util.HashMap;

public class ShowActivity extends AppCompatActivity implements ProductContract.IProductView {

    private String pid;
    private ImageView image;
    private TextView title ,subhead ,price ,createtime;
    private ProductPresenter productPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
        initData();
    }
    /**
     *  操作视图的方法
     */
    private void initView() {
        //获取上一个页面传过来的 pid
        Intent intent = getIntent();
        pid = intent.getStringExtra("pid");
        //获取控价ID title subhead price date
        image = findViewById(R.id.image);
        title = findViewById(R.id.title);
        subhead = findViewById(R.id.subhead);
        price = findViewById(R.id.price);
        createtime = findViewById(R.id.createtime);
        //实例化presenter层
        productPresenter = new ProductPresenter(this);
    }

    /**
     * 操作数据得方法
     */
    private void initData() {
        //创建一个集合
        HashMap<String,String> map = new HashMap<>();
        map.put("pid",pid+"");
        //调用访问数据的方法
        if (productPresenter!=null){
            productPresenter.showProduct(HomeApi.PRODUCT_API,map,ProductBean.class);
        }
    }

    /**
     * 成功的方法
     * @param object
     */
    @Override
    public void success(Object object) {
        //获取到数据 强转  赋值给数据
        ProductBean productBean = (ProductBean) object;
        //开始赋值 subhead ,price ,date;
        title.setText(productBean.data.title);
        String[] images =productBean.data.images.split("\\|");
        Glide.with(this).load(images[0]).into(image);
        subhead.setText(productBean.data.subhead);
        price.setText(productBean.data.price);
        createtime.setText(productBean.data.createtime);
    }

    /**
     * 失败的方法
     * @param string
     */
    @Override
    public void fail(String string) {
        //吐司
        Toast.makeText(ShowActivity.this,string,Toast.LENGTH_SHORT).show();
    }
}
