package com.personal.xingji.xingji.tianmao.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.personal.xingji.xingji.R;
import com.personal.xingji.xingji.tianmao.bean.ProductBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TianMaoProductView extends LinearLayout {

    private Context context;

    private LinearLayout ll_left,ll_right;

    private List<ProductBean> products = new ArrayList<>();

    public TianMaoProductView(Context context) {
        super(context);
        this.context = context;
        init(context);
    }

    public TianMaoProductView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(context);
    }


    private void init(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.tian_mao_product,null);
        addView(view);
        ll_left = view.findViewById(R.id.ll_tian_mao_product_left);
        ll_right = view.findViewById(R.id.ll_tian_mao_product_right);

        initProducts();

        addProductToView();
    }

    private void initProducts(){
        for(int i = 0 ; i < 50;i++){
            ProductBean bean = new ProductBean();
            bean.setComment("很舒服 质量很好");
            bean.setCoupon("3元券");
            bean.setGetProductName("情侣睡袍秋冬浴袍睡衣男女加厚绒法兰珊瑚绒家---"+i);
            bean.setPrice("39");
            bean.setProductUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545714960567&di=3607fb98c5df7602c97a2478a3d6fec3&imgtype=0&src=http%3A%2F%2Fpic30.nipic.com%2F20130626%2F2929309_160734294174_2.jpg");
            bean.setShopName("订裔浪");
            products.add(bean);
        }
    }

    private void addProductToView(){
        for(int i = 0 ; i < products.size() ; i++){
            View view = LayoutInflater.from(context).inflate(R.layout.tian_mao_product_child,null);
            setViewData(products.get(i),view);
            if(i%2 == 1){
                ll_right.addView(view);
            }else {
                ll_left.addView(view);
            }
        }
    }

    private void setViewData(ProductBean bean , View view){
        ImageView img = view.findViewById(R.id.img_tian_mao_product_child);
        TextView tv_shopName = view.findViewById(R.id.tv_shop_name_tian_mao_product_child);
        TextView tv_name = view.findViewById(R.id.tv_product_name_tian_mao_product_child);
        TextView tv_coupon = view.findViewById(R.id.tv_product_coupon_tian_mao_product_child);
        TextView tv_price= view.findViewById(R.id.tv_product_price_tian_mao_product_child);
        TextView tv_comment = view.findViewById(R.id.tv_product_comment_tian_mao_product_child);

        Picasso.get().load(bean.getProductUrl()).into(img);
        tv_shopName.setText(bean.getShopName());
        tv_name.setText(bean.getProductName());
        tv_coupon.setText(bean.getCoupon());
        tv_price.setText(bean.getPrice());
        tv_comment.setText(bean.getComment());
    }

}
