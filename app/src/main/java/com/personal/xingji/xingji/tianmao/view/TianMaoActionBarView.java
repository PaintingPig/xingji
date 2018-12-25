package com.personal.xingji.xingji.tianmao.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.personal.xingji.xingji.R;
import com.personal.xingji.xingji.utils.UIUtils;

public class TianMaoActionBarView extends RelativeLayout {

    private static final float MAX_MOVE_DISTANCE = UIUtils.dip2px(100);

    //搜索栏的位置
    private RelativeLayout rl_actionbar_bottom;

    //搜索栏的layoutParams
    private LayoutParams rl_actionbar_bottom_lp,img_bt_title_lp;

    private ImageButton img_bt_scan,img_bt_qr_code;

    private TextView img_bt_title;

    //历史移动距离
    private float historyMoveDistanceX=0,historyMoveDistanceY = 0;

    private float defaultLeftMargin,defaultRightMargin,defaultTopMargin;

    private float maxLeftDistance,maxRightDistance,maxTopDistance;

    public TianMaoActionBarView( Context context) {
        super(context);
        init(context);
    }

    public TianMaoActionBarView( Context context,  AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        View view = View.inflate(context, R.layout.tian_mao_actionbar,null);
        addView(view);
        rl_actionbar_bottom = view.findViewById(R.id.rl_actionbar_bottom);
        rl_actionbar_bottom_lp = (LayoutParams) rl_actionbar_bottom.getLayoutParams();

        img_bt_scan = view.findViewById(R.id.img_bt_scan);
        img_bt_qr_code = view.findViewById(R.id.img_bt_qr_code);
        img_bt_title = view.findViewById(R.id.img_bt_title);
        img_bt_title_lp = (LayoutParams) img_bt_title.getLayoutParams();

    }

    /**
     * 重新绘制layout的长宽
     * @param widthMeasureSpec：
     * @param heightMeasureSpec：
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width =  MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec) ;
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec) ;
        super.onMeasure(MeasureSpec.makeMeasureSpec(width,widthMode), MeasureSpec.makeMeasureSpec(height,heightMode));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    /**
     * 设置移动距离
     * @param preY:点击事件down的Y坐标
     * @param currentY :点击事件移动的event
     */
    public void setMovement(float preY,float currentY){
        //计算需要移动的距离
        float moveDistance = preY-currentY;
        if(historyMoveDistanceX == MAX_MOVE_DISTANCE ){
            if(historyMoveDistanceY >=0){
                if(historyMoveDistanceY == MAX_MOVE_DISTANCE && moveDistance > 0){
                    return;
                }
                if(!(historyMoveDistanceY ==0 && moveDistance < 0)){
                    moveY(moveDistance);
                    return;
                }

            }
        }
        if(historyMoveDistanceX == 0 && moveDistance < 0){
            return;
        }
        moveX(moveDistance);

    }

    private void moveY(float moveDistance){
        historyMoveDistanceY = historyMoveDistanceY + moveDistance >= MAX_MOVE_DISTANCE ? MAX_MOVE_DISTANCE :historyMoveDistanceY + moveDistance ;
        historyMoveDistanceY = historyMoveDistanceY <= 0 ? 0 : historyMoveDistanceY;
        rl_actionbar_bottom_lp.topMargin = (int) (defaultTopMargin - (historyMoveDistanceY * maxTopDistance / MAX_MOVE_DISTANCE));
        rl_actionbar_bottom.setLayoutParams(rl_actionbar_bottom_lp);

        img_bt_title_lp.topMargin = (int) (10 - (historyMoveDistanceY * maxTopDistance / MAX_MOVE_DISTANCE));
        img_bt_title.setLayoutParams(img_bt_title_lp);
    }

    void moveX(float moveDistance){
        //叠加上历史移动距离
        historyMoveDistanceX = historyMoveDistanceX + moveDistance >= MAX_MOVE_DISTANCE ? MAX_MOVE_DISTANCE : historyMoveDistanceX + moveDistance;
        historyMoveDistanceX = historyMoveDistanceX < 0 ? 0 : historyMoveDistanceX;


        //left需要移动距离
        float leftX = historyMoveDistanceX * maxLeftDistance / MAX_MOVE_DISTANCE + defaultLeftMargin;
        float rightX = historyMoveDistanceX * maxRightDistance/MAX_MOVE_DISTANCE + defaultRightMargin;

        rl_actionbar_bottom_lp.leftMargin = (int) leftX;
        rl_actionbar_bottom_lp.rightMargin = (int) rightX;

        rl_actionbar_bottom.setLayoutParams(rl_actionbar_bottom_lp);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if(hasWindowFocus){
            defaultLeftMargin = rl_actionbar_bottom_lp.leftMargin;
            defaultRightMargin = rl_actionbar_bottom_lp.rightMargin;
            defaultTopMargin = rl_actionbar_bottom_lp.topMargin;
            maxLeftDistance = img_bt_scan.getRight() - rl_actionbar_bottom.getLeft() + UIUtils.dip2px(10);//左边能够移动的最大位移
            maxRightDistance = rl_actionbar_bottom.getRight() - img_bt_qr_code.getLeft()+ UIUtils.dip2px(10);;//右边能够移动的最大位移
            maxTopDistance = defaultTopMargin - UIUtils.dip2px(10);//上边能够移动的最大距离
        }
    }
}
