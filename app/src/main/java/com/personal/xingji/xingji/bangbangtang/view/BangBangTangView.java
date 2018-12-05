package com.personal.xingji.xingji.bangbangtang.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.personal.xingji.xingji.utils.MathUtil;

public class BangBangTangView extends View {

    //view的高度和宽度
    private int layerHeight,layerWidth;
    //paint
    private Paint linePaint;

    //每次扩展的值
    private int addValue = 10;

    //需要显示的圆圈的角度
    private int circleAngle = 10;

    private Bitmap bitmap;


    public BangBangTangView(Context context){
        super(context);
        initPaint();
    }

    public BangBangTangView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        layerHeight = getHeight();
        layerWidth = getWidth();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(bitmap == null){
            bitmap = Bitmap.createBitmap(layerWidth,layerHeight,Bitmap.Config.ARGB_8888);
        }
        Canvas tmpCanvas = new Canvas();
        tmpCanvas.setBitmap(bitmap);


        int centerX = layerWidth/2;
        int centerY = layerHeight/2;

        RectF rectF = new RectF();
        rectF.left = centerX - addValue;
        rectF.top = centerY - addValue;
        rectF.right = centerX+addValue;
        rectF.bottom = centerY+addValue;

        tmpCanvas.drawArc(rectF,0,circleAngle,false,linePaint);

        //如果角度到350了那么需要绘制一条横线牵引出去
        if(circleAngle == 350){

            int X = (int) (centerX + MathUtil.cos(10) * addValue );
            int Y = (int) (centerY - MathUtil.sin(10) * addValue );

            addValue += 10;

            Path path = new Path();
            path.moveTo(X,Y);
            path.quadTo(X,Y-2,centerX+addValue,centerY);

            tmpCanvas.drawPath(path,linePaint);
//            tmpCanvas.drawLine(X,Y,centerX+addValue,centerY,linePaint);
            circleAngle = 0;
        }else {
            circleAngle +=5;
        }

        canvas.drawBitmap(bitmap,0,0,linePaint);
        invalidate();

    }

    private void initPaint(){
        linePaint = new Paint();
        linePaint.setColor(Color.RED);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setAntiAlias(true);
        linePaint.setTextSize(40);
    }

}
