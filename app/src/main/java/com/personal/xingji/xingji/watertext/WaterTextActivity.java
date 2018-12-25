package com.personal.xingji.xingji.watertext;

import android.app.Activity;
import android.os.Bundle;

import com.personal.xingji.xingji.R;
import com.personal.xingji.xingji.watertext.view.WaterTextView;

public class WaterTextActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_text);

        WaterTextView waterTextView= findViewById(R.id.water_text);
    }
}
