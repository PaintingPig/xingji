package com.personal.xingji.xingji.liziwenzi;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.personal.xingji.xingji.R;
import com.personal.xingji.xingji.liziwenzi.realaction.BackgroundColor;
import com.personal.xingji.xingji.liziwenzi.realaction.ColorValue;
import com.personal.xingji.xingji.liziwenzi.realaction.LiZiViewFactory;
import com.personal.xingji.xingji.liziwenzi.realaction.TextSizeValue;
import com.personal.xingji.xingji.liziwenzi.realaction.TextValue;
import com.yasic.library.particletextview.MovingStrategy.RandomMovingStrategy;
import com.yasic.library.particletextview.Object.ParticleTextViewConfig;
import com.yasic.library.particletextview.View.ParticleTextView;

public class LiziwenziActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liziwenzi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ParticleView particleView = findViewById(R.id.particleTextView);
        LiZiViewFactory liZiViewFactory = new LiZiViewFactory();
        liZiViewFactory.setColorValue(new ColorValue(Color.RED));
        liZiViewFactory.setTextValue(new TextValue("粒子散色测试"));
        liZiViewFactory.setBackgroundColor(new BackgroundColor(Color.RED));
        liZiViewFactory.setTextSizeValue(new TextSizeValue(100));
        liZiViewFactory.setStartMove(true);
        particleView.setLiZiFactory(liZiViewFactory);

    }

}
