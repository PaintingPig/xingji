package com.personal.xingji.xingji.liziwenzi.bean;

import java.util.ArrayList;
import java.util.List;

public class LiZiWenZiBean {

    private final int PART_NUM = 30;

    //文字颜色
    private String  color ;

    //原始位置X
    private int originX;

    //原始位置Y
    private int originY;

    //粒子移动目标点X
    private int destinationX;

    //粒子移动目标点Y
    private int destinationY;

    //粒子移动的轨迹
    private List<MoveBean> movePoint = new ArrayList<>();

    //当前移动的位置
    private int currentMovePosition ;

    public void setMovePoint(){
        int tmpX = (destinationX - originX)/PART_NUM;
        int tmpY = (destinationY - originY)/PART_NUM;
        for(int i = 0 ; i < PART_NUM ; i++) {
            MoveBean moveBean = new MoveBean();
            moveBean.setMoveX(originX + tmpX*i);
            moveBean.setMoveY(originY+tmpY*i);
            movePoint.add(moveBean);
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getOriginX() {
        return originX;
    }

    public void setOriginX(int originX) {
        this.originX = originX;
    }

    public int getOriginY() {
        return originY;
    }

    public void setOriginY(int originY) {
        this.originY = originY;
    }

    public int getDestinationX() {
        return destinationX;
    }

    public void setDestinationX(int destinationX) {
        this.destinationX = destinationX;
    }

    public int getDestinationY() {
        return destinationY;
    }

    public void setDestinationY(int destinationY) {
        this.destinationY = destinationY;
    }

    public List<MoveBean> getMovePoint() {
        return movePoint;
    }

    public int getCurrentMovePosition() {
        return currentMovePosition;
    }

    public void setCurrentMovePosition(int currentMovePosition) {
        this.currentMovePosition = currentMovePosition;
    }

    public void setMovePoint(List<MoveBean> movePoint) {
        this.movePoint = movePoint;
    }

    public class MoveBean{
        int moveX;
        int moveY;

        public int getMoveX() {
            return moveX;
        }

        public void setMoveX(int moveX) {
            this.moveX = moveX;
        }

        public int getMoveY() {
            return moveY;
        }

        public void setMoveY(int moveY) {
            this.moveY = moveY;
        }
    }
}
