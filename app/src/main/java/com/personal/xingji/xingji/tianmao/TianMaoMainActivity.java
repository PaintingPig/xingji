package com.personal.xingji.xingji.tianmao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;


import com.personal.xingji.xingji.R;
import com.personal.xingji.xingji.tianmao.adapter.TianMaoActivityRecycleAdapter;
import com.personal.xingji.xingji.tianmao.view.TianMaoActionBarView;
import com.personal.xingji.xingji.tianmao.view.TianMaoToolBarView;

public class TianMaoMainActivity extends AppCompatActivity {

    private TianMaoActionBarView tianMaoActionBarView;
    private float preY;
    private RecyclerView rcv_activity_tian_mao_main;
    private TianMaoActivityRecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.activity_tian_mao_main);
        tianMaoActionBarView = findViewById(R.id.action_bar);
        rcv_activity_tian_mao_main = findViewById(R.id.rcv_activity_tian_mao_main);
        adapter = new TianMaoActivityRecycleAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rcv_activity_tian_mao_main.setLayoutManager(staggeredGridLayoutManager);
        rcv_activity_tian_mao_main.setAdapter(adapter);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            Log.e("dingqiang","otionEvent.ACTION_DOWN");
            preY = event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            Log.e("dingqiang","otionEvent.ACTION_MOVE");
            tianMaoActionBarView.setMovement(preY,event.getY());
            preY = event.getY();
        }
        return super.dispatchTouchEvent(event);
    }
}
