package com.kfc.gitdemo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Picture;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.kcode.autoscrollviewpager.view.AutoScrollViewPager;
import com.kcode.autoscrollviewpager.view.BaseViewPagerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.viewPager)
    AutoScrollViewPager viewPager;
    @InjectView(R.id.button)
    Button button;
    private String[] paths = {"https://ss3.baidu.com/-fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=c493b482b47eca800d053ee7a1229712/8cb1cb1349540923abd671df9658d109b2de49d7.jpg",
            "https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=45fbfa5555da81cb51e684cd6267d0a4/2f738bd4b31c8701491ea047237f9e2f0608ffe3.jpg",
            "https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=ae0e95c0fc1986185e47e8847aec2e69/0b46f21fbe096b63eb314ef108338744ebf8ac62.jpg",
            "https://ss3.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=1fad2b46952397ddc9799f046983b216/dc54564e9258d109c94bbb13d558ccbf6d814de2.jpg",
            "https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=ff0999f6d4160924c325a51be406359b/86d6277f9e2f070861ccd4a0ed24b899a801f241.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* *//**
         * 隐藏状态栏,ActionBar和导航栏
         *//*
        //去掉状态栏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);//去掉状态栏的代码一定要在setContentView方法之前执行
        //隐藏导航栏
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(option);
        //隐藏ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();*/
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        /**
         * 隐藏ActionBar，状态栏和导航栏透明
         */
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

       button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "不错", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, RefreshActivity.class);
                startActivity(intent);
            }
        });
        viewPager.setAdapter(new BaseViewPagerAdapter(this,initData(),listener) {
            @Override
            public void loadImage(ImageView view, int position, Object o) {
                Picasso.with(MainActivity.this).load(o.toString()).into(view);
            }
        });
    }

   /* *//**
     * 标准沉浸式模式
     *//*
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }*/
     private List<String> initData(){
        List<String> data=new ArrayList<>();
        Picture picture;
        for (int i = 0; i <paths.length; i++) {
            data.add(paths[i]);
        }
        return data;
    }

   @Override
    protected void onDestroy() {
        super.onDestroy();
        //记得在销毁的时候调用onDestroy()方法,用来销毁定时器
        viewPager.onDestroy();
    }
    //定义点击事件
    private BaseViewPagerAdapter.OnAutoViewPagerItemClickListener listener=new BaseViewPagerAdapter.OnAutoViewPagerItemClickListener() {
        @Override
        public void onItemClick(int position, Object o) {
            Toast.makeText(MainActivity.this, "您选择了"+position+"位美女", Toast.LENGTH_SHORT).show();
        }
    };
}
