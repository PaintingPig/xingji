package com.personal.xingji.xingji.liziwenzi.realaction;

/**
 * 创建粒子工厂
 */
public class LiZiViewFactory {
    //粒子工厂实例
    public static LiZiViewFactory instance;
    //颜色值
    private ColorValue colorValue;
    private TextValue textValue;
    private BackgroundColor backgroundColor;
    private TextSizeValue textSizeValue;
    private boolean startMove;
    /**
     * 获取创建粒子view的实例，默认为单例模式
     *
     * @return 粒子view的实例
     */
    public synchronized static LiZiViewFactory getInstance() {
        return getInstance(false);
    }

    /**
     * 获取创建粒子view的实例
     *
     * @param shouldMakeNewInstance : 是否创建新的实例
     *                              true：创建新的实例，对原先创建的单例没有影响
     *                              false:创建单例的实例
     * @return 实例
     */
    public synchronized static LiZiViewFactory getInstance(boolean shouldMakeNewInstance) {
        if (shouldMakeNewInstance) {
            return new LiZiViewFactory();
        }
        if (instance == null) {
            instance = new LiZiViewFactory();
        }
        return instance;
    }


    public boolean isStartMove() {
        return startMove;
    }

    public void setStartMove(boolean startMove) {
        this.startMove = startMove;
    }

    public ColorValue getColorValue() {
        return colorValue;
    }

    public void setColorValue(ColorValue colorValue) {
        this.colorValue = colorValue;
    }

    public TextValue getTextValue() {
        return textValue;
    }

    public void setTextValue(TextValue textValue) {
        this.textValue = textValue;
    }

    public BackgroundColor getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(BackgroundColor backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public TextSizeValue getTextSizeValue() {
        return textSizeValue;
    }

    public void setTextSizeValue(TextSizeValue textSizeValue) {
        this.textSizeValue = textSizeValue;
    }
}
