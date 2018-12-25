package com.personal.xingji.xingji.tianmao.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.personal.xingji.xingji.R;
import com.squareup.picasso.Picasso;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TianMaoToolBarGridAdapter extends BaseAdapter {

    private LinkedHashMap<String,String> data ;

    private List<String> tags = new ArrayList<>();

    private Context context;

    public TianMaoToolBarGridAdapter(LinkedHashMap<String,String> data, Context context){
        this.data = data;
        this.context = context;
        getDataTags();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.tian_mao_tool_bar_child_grid,null);
            holder.setImageView((ImageView) convertView.findViewById(R.id.img_tool_bar_title));
            holder.setTextView((TextView) convertView.findViewById(R.id.tv_tool_bar_title));
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.getTextView().setText(tags.get(position));
        Picasso.get().
                load(data.get(tags.get(position)))
                .error(R.drawable.tian_mao_camera)
                .into(holder.getImageView());
        return convertView;
    }

    private void getDataTags(){
        Iterator iterator = data.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry next = (Map.Entry) iterator.next();
            tags.add((String) next.getKey());
        }
    }

    private class ViewHolder{
        private ImageView imageView;
        private TextView textView;

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        public TextView getTextView() {
            return textView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }
    }
}

