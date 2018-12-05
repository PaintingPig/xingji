package com.personal.xingji.xingji.liziwenzi.realaction;

import android.graphics.Color;

public class ColorValue {
    private Integer intValue;

    public ColorValue(String value) {
        intValue = Color.parseColor(value);
    }

    public ColorValue(Integer value) {
        intValue = value;
    }

    /**
     * 设置颜色值，Integer和String只能存在一个，设置了一个，另外一个会被置空
     *
     * @param value 颜色值
     */
    public void setValue(String value) {
        intValue = Color.parseColor(value);
    }

    /**
     * 设置颜色值，Integer和String只能存在一个，设置了一个，另外一个会被置空
     *
     * @param value 颜色值
     */
    public void setValue(int value) {
        intValue = value;
    }

    /**
     * 获取颜色值
     *
     * @return
     */
    public int getValue() {
        return intValue;
    }
}
