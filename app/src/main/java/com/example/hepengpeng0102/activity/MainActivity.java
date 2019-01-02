package com.example.hepengpeng0102.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.hepengpeng0102.R;
import com.example.hepengpeng0102.adapter.MyAdapter;
import com.example.hepengpeng0102.api.HomeApi;
import com.example.hepengpeng0102.bean.HomeBean;
import com.example.hepengpeng0102.contract.ProductContract;
import com.example.hepengpeng0102.presenter.ProductPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductContract.IProductView {

    private GridView gv;
    private ProductPresenter productPresenter;
    private MyAdapter myAdapter;
    private HomeBean homeBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    /**
     * 操作视图的方法
     */
    private void initView() {
        //获取资源ID
        gv = findViewById(R.id.gv);
        //创建presenter 实例
        productPresenter = new ProductPresenter(this);
        //设置适配器
        myAdapter = new MyAdapter(MainActivity.this);
        gv.setAdapter(myAdapter);
    }

    /**
     * 操作数据的方法
     */
    private void initData() {
        productPresenter.showProduct(HomeApi.HOME_API,null,HomeBean.class);
        //点击事件 跳转到页面详情
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,ShowActivity.class);
                intent.putExtra("pid",homeBean.data.miaosha.list.get(position).pid);
                startActivity(intent);
            }
        });
        //设置长按点击事件
        gv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                myAdapter.removelist(position);
                Toast.makeText(MainActivity.this,"删除成功！",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    /**
     * 成功回调的方法
     */
    @Override
    public void success(Object object) {
        homeBean = (HomeBean) object;
        if (object!=null){
            myAdapter.setlist(homeBean.data.miaosha.list);
        }
    }

    /**
     * 失败回调的方法
     */
    @Override
    public void fail(String string) {
        Toast.makeText(MainActivity.this,string,Toast.LENGTH_SHORT).show();
    }

    //页面销毁的方法

    @Override
    protected void onDestroy() {
        super.onDestroy();
        productPresenter.setcache();
    }
}
