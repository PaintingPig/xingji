package com.personal.xingji.xingji.tianmao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.personal.xingji.xingji.R;

public class TianMaoActivityRecycleAdapter extends RecyclerView.Adapter {

    private Context context;

    private static final int TYPE_HEADER = 0x00;
    private static final int TYPE_BODY = 0x01;
    private static final int TYPE_FOOTER = 0x02;

    public TianMaoActivityRecycleAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i == TYPE_HEADER){
            View view = LayoutInflater.from(context).inflate(R.layout.activity_tian_mao_tool_bar,viewGroup,false);
            HeaderHolder holder = new HeaderHolder(view);
            return holder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }


    private class HeaderHolder extends RecyclerView.ViewHolder{

        public HeaderHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_HEADER;
        }else
        return TYPE_BODY;
    }


}
