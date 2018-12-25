package com.personal.xingji.xingji.tianmao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.personal.xingji.xingji.R;
import com.personal.xingji.xingji.tianmao.bean.ToolBarAdsBean;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TianMaoToolBarRecyclerAdapter extends RecyclerView.Adapter {

    private Context context;

    private List<ToolBarAdsBean> ads;

    public  TianMaoToolBarRecyclerAdapter(List<ToolBarAdsBean> ads,Context context){
        this.context = context;
        this.ads = ads;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.tian_mao_tool_bar_child_recycler,viewGroup,false);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        myHolder holder = (myHolder) viewHolder;
        Log.e("dingqiang","url = " +ads.get(i % ads.size()).getImgUrl() );
        Picasso.get().load(ads.get(i % ads.size()).getImgUrl()).into(holder.getImageView());
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }


    private class myHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        public myHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_tian_mao_tool_bar_child_recycler);
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }
    }
}
