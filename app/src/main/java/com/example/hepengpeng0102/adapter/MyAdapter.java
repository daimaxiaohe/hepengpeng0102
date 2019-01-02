package com.example.hepengpeng0102.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hepengpeng0102.R;
import com.example.hepengpeng0102.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * はすてすゃの
 * 2019-01-02.
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<HomeBean.Data.Miao.Home> list;
    public MyAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }
    //给集合赋值的方法
    public void setlist(List<HomeBean.Data.Miao.Home> list){
        if (list!=null){
            this.list =list;
        }
        notifyDataSetChanged();
    }
    //删除的方法
    public void removelist(int position){
        this.list.remove(position);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.gv_item,parent,false);
            holder = new ViewHolder(convertView);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.settext(position);
        return convertView;
    }
    //复用类
    class ViewHolder{
        ImageView gv_img;
        TextView gv_price,gv_title;
        public ViewHolder(View convertView) {
            gv_img = convertView.findViewById(R.id.gv_img);
            gv_title = convertView.findViewById(R.id.gv_title);
            gv_price = convertView.findViewById(R.id.gv_price);
            convertView.setTag(this);
        }

        public void settext(int position) {
            gv_title.setText(list.get(position).title);
            gv_price.setText(list.get(position).price);
            String[] images = list.get(position).images.split("\\|");
            Glide.with(context).load(images[0]).into(gv_img);
        }
    }
}
