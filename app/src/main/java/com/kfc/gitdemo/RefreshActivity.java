package com.kfc.gitdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RefreshActivity extends AppCompatActivity {

    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;
    @InjectView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        ButterKnife.inject(this);
        //设置下拉出现小圆圈是否是缩放出现,出现的位置,最大的下拉位置
        refreshLayout.setProgressViewOffset(true,50,200);
        //设置下拉圆圈的大小,两个值 LARGE大,DEFAULT默认
        refreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        //设置下拉圆圈上的颜色:蓝色、绿色、橙色、红色
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_dark,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark
        );
        //通过setEnabled设置禁用下拉刷新
        //refreshLayout.setEnabled(false);
        //设置下拉圆圈的背景
        //refreshLayout.setProgressBackgroundColor(R.color.colorAccent);
        //设置手势下拉刷新的监听,一般这里重新请求一下接口
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新动画开始后回调到此方法
                Toast.makeText(RefreshActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //停止刷新,圆圈消失
                        refreshLayout.setRefreshing(false);
                    }
                },5000);
            }
        });
    }
}
