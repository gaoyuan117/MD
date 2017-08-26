package com.gaoyuan.materialdesign.recycler;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gaoyuan.materialdesign.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private WrapRecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private List<String> mList;
    private View headView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        headView  = LayoutInflater.from(this).inflate(R.layout.head_view,null);
        mRecyclerView = (WrapRecyclerView) findViewById(R.id.recycler);
        mList = new ArrayList<>();
        mRecyclerView.addHeaderView(headView);
        for (int i = 0; i < 15; i++) {
            mList.add("哈哈" + i);
        }

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false);
        StaggeredGridLayoutManager staggerManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mAdapter = new RecyclerAdapter(this, mList);
//        mRecyclerView.addItemDecoration(new RecyclerViewDivider(this,LinearLayoutManager.VERTICAL,30, ContextCompat.getColor(this, R.color.white)));
//        mRecyclerView.addItemDecoration(new RecyclerViewGridDivider(this,15, ContextCompat.getColor(this, R.color.ripple_color)));
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }
}



