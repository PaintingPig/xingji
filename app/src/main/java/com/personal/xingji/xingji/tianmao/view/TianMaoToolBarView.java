package com.personal.xingji.xingji.tianmao.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.personal.xingji.xingji.R;
import com.personal.xingji.xingji.tianmao.adapter.TianMaoToolBarGridAdapter;
import com.personal.xingji.xingji.tianmao.adapter.TianMaoToolBarRecyclerAdapter;
import com.personal.xingji.xingji.tianmao.bean.ToolBarAdsBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TianMaoToolBarView extends RelativeLayout {

    private LinkedHashMap<String,String> icons = new LinkedHashMap<>();

    private List<ToolBarAdsBean> ads = new ArrayList<>();


    private GridView gv_tool;

//    private ViewPager vp_tool;

    private RecyclerView rcv_tool_bar;

    public TianMaoToolBarView(Context context) {
        super(context);
        init(context);
    }

    public TianMaoToolBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        initData(context);
        initViews(context);
    }
    LinearLayoutManager linearLayoutManager;
    private void initViews(Context context){
        View view = View.inflate(context, R.layout.tian_mao_tool_bar,null);
        addView(view);
        gv_tool = view.findViewById(R.id.gv_tool);
//        vp_tool = view.findViewById(R.id.vp_tool);
        rcv_tool_bar = view.findViewById(R.id.rcv_tool_bar);

        TianMaoToolBarGridAdapter gridAdapter = new TianMaoToolBarGridAdapter(icons,context);
        gv_tool.setAdapter(gridAdapter);

        linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        rcv_tool_bar.setLayoutManager(linearLayoutManager);
        rcv_tool_bar.setAdapter(new TianMaoToolBarRecyclerAdapter(ads,getContext()));
        rcv_tool_bar.scrollToPosition(Integer.MAX_VALUE/2);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rcv_tool_bar);

        rcv_tool_bar.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
               if(newState == RecyclerView.SCROLL_STATE_IDLE){
                   int position = linearLayoutManager.findFirstVisibleItemPosition() % ads.size();
                   changeBackGroundColor(ads.get(position).getBackColor());
               }
            }
        });

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                rcv_tool_bar.smoothScrollToPosition(linearLayoutManager.findFirstVisibleItemPosition()+1);
            }
        },0,5,TimeUnit.SECONDS);
    }

    private void initData(Context context){
        icons.put("苏宁易购","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545128558104&di=79262aa4bf497e800959dedcbaf4d8e4&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F0df431adcbef7609d45e67bc24dda3cc7cd99eb8.jpg");
        icons.put("天猫超市","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545128558104&di=68293c52c09a7e76e8dd22bb5d529f53&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F5%2F58943653d028d.jpg");
        icons.put("天猫国际","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545128558102&di=f68618cfbebba8d52e064fd0beeb91a8&imgtype=0&src=http%3A%2F%2Fpic18.nipic.com%2F20120111%2F8430999_155806277106_2.jpg");
        icons.put("聚划算","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545128558096&di=a2b1388e83af79ffc8abf62394e9a1a4&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F0b55b319ebc4b7454d716709c5fc1e178b8215c3.jpg");
        icons.put("天猫闪降","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545128558095&di=c8371afb0dbe5a0aaf41c387cb1a419c&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F962bd40735fae6cdd0dcb04505b30f2442a70fad.jpg");

        icons.put("免费领水果","http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545123051892&di=0e738d35913cc680f01267b801b8c935&imgtype=0&src=http%3A%2F%2Fsrc.onlinedown.net%2Fsupply%2F170210_logo%2Ff07bcb33382c139a4c1c07.jpg");
        icons.put("积分兑红包","http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545123051883&di=c8aed15646ed2358adaddae52b5dfb6a&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201406%2F07%2F20140607125024_CyW4z.jpeg");
        icons.put("粉丝福利社","http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545123051879&di=d8b62ef0e56260205ef3107965941401&imgtype=0&src=http%3A%2F%2Ft0.qlogo.cn%2Fmbloghead%2Fe232337113dd1164d068%2F180");
        icons.put("邀请赚红包","http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545123051868&di=f996c8839d9e202a7d1d049e903d7f2b&imgtype=0&src=http%3A%2F%2Fwww.17qq.com%2Fimg_qqtouxiang%2F76625990.jpeg");
        icons.put("分类","http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545123051865&di=765fd2d9b898a83c07e64bfe30afff07&imgtype=0&src=http%3A%2F%2Fimg0.ph.126.net%2FSYXBfYnIQcqJkRekvJM3zw%3D%3D%2F3003056526626306883.jpg");

        initAds(context);
    }


    private void initAds(Context context){
        ToolBarAdsBean b0 = new ToolBarAdsBean();
        b0.setBackColor("#FF0000");
        b0.setImageView(getADImageVie(context));
        b0.setImgUrl("http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545717926&di=496220a97c98f6e1b60d0d6ab95ea906&imgtype=jpg&er=1&src=http%3A%2F%2F00.minipic.eastday.com%2F20170322%2F20170322101109_43103656e73a2038299ec17c648fc6c6_15.jpeg");
        Picasso.get().load(b0.getImgUrl()).into(b0.getImageView());

        ads.add(b0);


        ToolBarAdsBean b1 = new ToolBarAdsBean();
        b1.setBackColor("#8B008B");
        b1.setImageView(getADImageVie(context));
        b1.setImgUrl("http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545123207460&di=7c5ca1d48cb8dc50ce139fb372e0de38&imgtype=0&src=http%3A%2F%2Fpic30.nipic.com%2F20130608%2F7447430_092225119000_2.jpg");
        Picasso.get().load(b1.getImgUrl()).into(b1.getImageView());
        ads.add(b1);

        ToolBarAdsBean b2 = new ToolBarAdsBean();
        b2.setBackColor("#00FF7F");
        b2.setImageView(getADImageVie(context));
        b2.setImgUrl("http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545123207450&di=c8b0b7b362546421d8e4825053ba3d09&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2%2F51d781f3a09ff.jpg");
        Picasso.get().load(b2.getImgUrl()).into(b2.getImageView());
        ads.add(b2);

        ToolBarAdsBean b3 = new ToolBarAdsBean();
        b3.setBackColor("#EEEE00");
        b3.setImageView(getADImageVie(context));
        b3.setImgUrl("http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545123207433&di=4342ab7656e0f044941a0f52f5b755b0&imgtype=0&src=http%3A%2F%2Fimg06.tooopen.com%2Fimages%2F20170313%2Ftooopen_sy_201702061469.jpg");
        Picasso.get().load(b3.getImgUrl()).into(b3.getImageView());
        ads.add(b3);
    }

    private ImageView getADImageVie(Context context){
        ImageView imageView = new ImageView(context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParams);

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    private void changeBackGroundColor(String color){
        gv_tool.setBackgroundColor(Color.parseColor(color));
    }





}
