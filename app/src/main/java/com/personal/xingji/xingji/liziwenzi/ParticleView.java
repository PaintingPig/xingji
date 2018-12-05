package com.personal.xingji.xingji.liziwenzi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.personal.xingji.xingji.liziwenzi.bean.LiZiWenZiBean;
import com.personal.xingji.xingji.liziwenzi.realaction.LiZiViewFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ParticleView extends View {

    private static final int GET_TEXT_UN_START = 0;
    private static final int GET_TEXT_START = 1;
    private static final int GET_TEXT_END = 2;

    //控制粒子文字显示
    private LiZiViewFactory liZiViewFactory;

    //屏幕的长宽
    private int layerWidth, layerHeight;

    //背景色
    private int backgroundColor;

    //文字顏色
    private int textColor;

    //文字画笔
    private Paint textPaint;

    //文字大小
    private int textSize;

    //採集文字點的間隔
    private int collectPointSpace = 4;

    //包含文字的整个画布
    private Bitmap bitmap;

    //整个画布的像素点
    private int[][] bitmapPoint;

    //获取文字点阵进行的进度
    private int getTextStatus = GET_TEXT_UN_START;


    private List<LiZiWenZiBean> wenZiPointList = new ArrayList<>();

    private  Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("dingqiang","handle");
            if (liZiViewFactory.isStartMove()) {
                invalidate();
            }
        }
    };

    public ParticleView(Context context) {
        super(context);
    }

    public ParticleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setLiZiFactory(LiZiViewFactory liZiFactory) {
        this.liZiViewFactory = liZiFactory;
        initValue();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        layerHeight = getHeight();
        layerWidth = getWidth();
    }


    /**
     *  onDraw 的调不一定可控，所以异步调用需要添加判断
     * @param canvas :
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getTextStatus == GET_TEXT_UN_START) {
            getDataSync();
            canvas.drawText("Loading......", 0, layerHeight / 2, textPaint);
            return;
        } else if (getTextStatus == GET_TEXT_START) {
            canvas.drawText("Loading......", 0, layerHeight / 2, textPaint);
            return;
        }
        //開始繪製文字
        for (LiZiWenZiBean bean : wenZiPointList) {
            textPaint.setColor(Color.parseColor(bean.getColor()));
            int currentPosition = bean.getCurrentMovePosition();
            LiZiWenZiBean.MoveBean moveBean = bean.getMovePoint().get(currentPosition);
            canvas.drawCircle(moveBean.getMoveX(), moveBean.getMoveY(), collectPointSpace / 2, textPaint);
            ++currentPosition;
            if (currentPosition == 30) {
                currentPosition = 0;
            }
            bean.setCurrentMovePosition(currentPosition);

        }
        handler.sendEmptyMessageDelayed(1, 50);

    }




    /**
     * 初始化一些数据
     */
    private void initValue() {
        backgroundColor = (int) liZiViewFactory.getBackgroundColor().getValue();
        textSize = liZiViewFactory.getTextSizeValue().getTextSize();
        textColor = liZiViewFactory.getColorValue().getValue();
        initPaint();
    }

    /**
     * 初始化paint
     */
    private void initPaint() {
        textPaint = new Paint();
        textPaint.setColor(Color.parseColor("#898989"));//设置文字颜色为黑色
        textPaint.setTextSize(textSize);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * 异步获取文字的点阵，防止加载过慢
     */
    private void getDataSync() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                //绘制文字
                drawText();
                //獲取畫布的佈局
                collectAllCanvasPoint();
            }
        }).start();

    }

    /**
     * 新建一个文本的bitmap
     */
    private void drawText() {
        getTextStatus = GET_TEXT_START;
        bitmap = Bitmap.createBitmap(layerWidth, layerHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(bitmap);
        canvas.drawColor(backgroundColor);//设置背景色
        canvas.drawText(liZiViewFactory.getTextValue().getText(), 0, layerHeight / 2, textPaint);
    }

    /**
     * 采集整个画布的点
     */
    private void collectAllCanvasPoint() {
        bitmapPoint = new int[bitmap.getWidth()][bitmap.getHeight()];
        for (int i = 0; i < bitmap.getWidth(); i += collectPointSpace) {
            for (int j = 0; j < bitmap.getHeight(); j += collectPointSpace) {
                int color = bitmap.getPixel(i, j);
                String strColor = getStringColor(color);

                if (strColor.equals("#898989")) {
                    LiZiWenZiBean bean = new LiZiWenZiBean();
                    bean.setColor(resetWenZiColor());
                    bean.setOriginX(i);
                    bean.setOriginY(j);
                    bean.setDestinationX(new Random().nextInt(layerWidth));
                    bean.setDestinationY(new Random().nextInt(layerHeight));
                    bean.setMovePoint();
                    wenZiPointList.add(bean);
                }
                bitmapPoint[i][j] = bitmap.getPixel(i, j);
            }
        }
        getTextStatus = GET_TEXT_END;
        postInvalidate();
    }

    /**
     * 重新设置点的颜色
     * @return ：
     */
    private String resetWenZiColor() {
        if (textColor != -1) {
            return getStringColor(textColor);
        }
        int red = new Random().nextInt(255);
        int green = new Random().nextInt(255);
        int blue = new Random().nextInt(255);
        return getStringColor(Color.rgb(red, green, blue));
    }

    /**
     * 将int型color值转成String类型
     * @param intColor ：Color 对应的int值
     * @return ：
     */
    private String getStringColor(int intColor) {
        String red = Integer.toHexString(Color.red(intColor));
        String green = Integer.toHexString(Color.green(intColor));
        String blue = Integer.toHexString(Color.blue(intColor));
        return "#" + dealSingleString(red) + dealSingleString(green) + dealSingleString(blue);
    }

    /**
     * 颜色String转换为2位
     * @param str ： R/G/B 的String值
     * @return ：
     */
    private String dealSingleString(String str) {
        if (str.length() == 1) {
            return "0" + str;
        } else {
            return str;
        }
    }

    /**
     * view 被销毁时回收Bitmap
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e("dingqiang","onDetachedFromWindow");
        bitmap.recycle();
        bitmap = null;
        handler.removeCallbacksAndMessages(null);
    }
}
