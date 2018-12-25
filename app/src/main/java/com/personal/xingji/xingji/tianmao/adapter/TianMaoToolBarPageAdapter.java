package com.personal.xingji.xingji.tianmao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.personal.xingji.xingji.tianmao.bean.ToolBarAdsBean;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TianMaoToolBarPageAdapter extends PagerAdapter {

    private Context context;

    private List<ToolBarAdsBean> imageViews ;

    public TianMaoToolBarPageAdapter(List<ToolBarAdsBean> imageViews,Context context){
        this.context = context;
        this.imageViews = imageViews;
    }

    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
    private ImageView getADImageVie(Context context,int position){
        ImageView imageView = new ImageView(context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParams);

        String url = "";
        switch (position%4){
            case 0:
                Picasso.get().load("http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545717926&di=496220a97c98f6e1b60d0d6ab95ea906&imgtype=jpg&er=1&src=http%3A%2F%2F00.minipic.eastday.com%2F20170322%2F20170322101109_43103656e73a2038299ec17c648fc6c6_15.jpeg").into(imageView);
                break;
            case 1:
                Picasso.get().load("http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545123207433&di=4342ab7656e0f044941a0f52f5b755b0&imgtype=0&src=http%3A%2F%2Fimg06.tooopen.com%2Fimages%2F20170313%2Ftooopen_sy_201702061469.jpg").into(imageView);
                break;
            case 2:
                Picasso.get().load("http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545123207450&di=c8b0b7b362546421d8e4825053ba3d09&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2%2F51d781f3a09ff.jpg").into(imageView);
                break;
            case 3:
                Picasso.get().load("http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545123207433&di=4342ab7656e0f044941a0f52f5b755b0&imgtype=0&src=http%3A%2F%2Fimg06.tooopen.com%2Fimages%2F20170313%2Ftooopen_sy_201702061469.jpg").into(imageView);
                break;
        }
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    int size = 0;
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int tmpPosition = position % (imageViews.size());
        View view = imageViews.get(tmpPosition).getImageView();
//        Log.e("dingqiang","view parent = " + view.getParent());
        Log.e("dingqiang","container = " + container +"\n" + container.getChildCount());

        if(view.getParent() == null){
            container.addView(view);
        }

        Picasso.get().load(imageViews.get(tmpPosition).getImgUrl()).into((ImageView) view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        int tmpPosition = position % (imageViews.size());
//        View view = imageViews.get(tmpPosition).getImageView();
//        Log.e("dingqiang","destroyItem view  = " + object);
//        Log.e("dingqiang","destroyItem view parent= " + ((View)object).getParent());
//          container.removeView((View) object);
//        Log.e("dingqiang","removeView view  = " + object);
//        Log.e("dingqiang","removeView view parent= " + ((View)object).getParent());
    }



}
