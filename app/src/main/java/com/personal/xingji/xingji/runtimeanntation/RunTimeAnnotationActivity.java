package com.personal.xingji.xingji.runtimeanntation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.personal.xingji.xingji.R;

import java.lang.reflect.Field;

public class RunTimeAnnotationActivity extends AppCompatActivity {
    private static final String TAG = "AnnotationActivity";

    @AnnotationTest(value = "annotationDefinedParam",address = 1000)
    private String annotationParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_time_annotation);

        try {
            explainAnnotation(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Log.i(TAG,"annotationParam = " + annotationParam);
        findViewById(R.id.activity_run_time_annotation_tv).setTag(annotationParam);//代码检查--逼死强迫症所以加上这句无意义的语句
    }

    /**
     * 解释运行时的注解
     * @param object，传递注解的类/class
     */
    private void explainAnnotation(Object object) throws IllegalAccessException {
        Class annotationClass = object.getClass();//获取Class对象
        Field fields[] = annotationClass.getDeclaredFields();//获取Class中包含的属性值
        for (Field field : fields){
            AnnotationTest annotationTest = field.getAnnotation(AnnotationTest.class);
            if(annotationTest == null) return;
            Log.i(TAG,"annotationTest.value = " + annotationTest.value());
            Log.i(TAG,"annotationTest address = " + annotationTest.address());

            //利用反射向其param写入值
            field.set(object,annotationTest.value());
        }
    }
}
