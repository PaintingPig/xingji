package com.personal.xingji.xingji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


import com.personal.xingji.xingji.runtimeanntation.RunTimeAnnotationActivity;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    GridView grid_view;

    private String []items = new String[]{"annotation反射RunTime方法","待定","待定","待定","待定","待定","待定","待定","待定","待定","待定"};
    private Class []itemActivities = new Class[]{RunTimeAnnotationActivity.class};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid_view = findViewById(R.id.grid_view);
        grid_view.setAdapter(new GridViewAdapter());
        grid_view.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent();
        if(i >= itemActivities.length){
            Toast.makeText(this,"未开发的功能",Toast.LENGTH_SHORT).show();
            return;
        }
        intent.setClass(this,itemActivities[i]);
        startActivity(intent);
    }

    private class GridViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int i) {
            return items[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view == null){
                view = View.inflate(MainActivity.this,R.layout.main_grid_child,null);
            }
            ViewHolder holder = (ViewHolder) view.getTag();
            if(holder == null){
                Log.i("dingqiang","null");
                holder = new ViewHolder(view);
            }
            holder.getTextView().setText(items[i]);
            return view;
        }

        private class ViewHolder {
            TextView textView;
             ViewHolder(View view){
                textView = view.findViewById(R.id.main_grid_child_tv);
                view.setTag(this);
            }
            TextView getTextView() {
                return textView;
            }

        }

    }

}
