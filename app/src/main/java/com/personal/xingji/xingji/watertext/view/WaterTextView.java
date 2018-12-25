package com.personal.xingji.xingji.watertext.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.personal.xingji.xingji.utils.MathUtil;

import java.util.concurrent.locks.Lock;

public class WaterTextView extends View {

    private int layerWidth,layerHeight;
    private Paint wavePaint;

    public WaterTextView(Context context) {
        super(context);
        init();
    }

    public WaterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        layerHeight = getHeight();
        layerWidth = getWidth();
    }

    public void init(){
        wavePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        wavePaint.setAntiAlias(true);
        wavePaint.setStyle(Paint.Style.STROKE);
        wavePaint.setColor(Color.RED);
    }


     Path path = new Path();
    float tmp = 0;
    @Override
    protected  void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        tmp-=0.1f;
        double w =4 * Math.PI/layerWidth;
        path.moveTo(0,layerHeight/2);
        for(float x = 0 ; x < layerWidth;x+=20){
            float y = (float) (20 * Math.sin(w * x + tmp)) + 200;
            Log.e("dingqiang","xx = " + y);
            path.lineTo(x,y);
        }
        path.lineTo(getRight(),0);
        canvas.drawPath(path,wavePaint);
        invalidate();
    }
}
