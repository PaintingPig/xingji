package com.personal.xingji.xingji.utils;

/**
 * 用于数学运算的工具类
 * @author dingqiang
 */
public class MathUtil {
    /**
     * Math 的sin方法，Math传递的是弧度
     * @param degree ：角度数值：如30度，返回0.4999999
     * @return
     */
    public static  double sin(double degree){
        return Math.sin(changeCommonDegreeToMath(degree));
    }

    /**
     * Math 的cos方法
     * @param degree:角度数值：如30度
     * @return
     */
    public static double cos(double degree){
        return Math.cos(changeCommonDegreeToMath(degree));
    }

    /**
     * 把普通如30度的值改变成Math中可输入的数值
     * @param degree
     * @return
     */
    public static double changeCommonDegreeToMath(double degree){
        return Math.PI * degree / 180;
    }

}
