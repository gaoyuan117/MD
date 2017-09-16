package com.gaoyuan.materialdesign.recycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;

import com.gaoyuan.materialdesign.R;
import com.gaoyuan.materialdesign.recycler.head.WrapRecyclerView;
import com.gaoyuan.materialdesign.recycler.touch.ItemTouchMoveListener;
import com.gaoyuan.materialdesign.recycler.touch.MyItemTouchHelperCallback;
import com.gaoyuan.materialdesign.recycler.touch.StartDragListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements StartDragListener{
    private WrapRecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private List<String> mList;
    private View headView;
    private ItemTouchHelper itemTouchHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        headView = LayoutInflater.from(this).inflate(R.layout.head_view, null);
        mRecyclerView = (WrapRecyclerView) findViewById(R.id.recycler);
        mList = new ArrayList<>();
//        mRecyclerView.addHeaderView(headView);
        for (int i = 0; i < 15; i++) {
            mList.add("哈哈" + i);
        }

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false);
        StaggeredGridLayoutManager staggerManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mAdapter = new RecyclerAdapter(this, mList, this);
//        mRecyclerView.addItemDecoration(new RecyclerViewDivider(this,LinearLayoutManager.VERTICAL,30, ContextCompat.getColor(this, R.color.white)));
//        mRecyclerView.addItemDecoration(new RecyclerViewGridDivider(this,15, ContextCompat.getColor(this, R.color.ripple_color)));
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

        itemTouchHelper();
    }

    /**
     * RecyclerView的条目动画
     */
    private void itemTouchHelper() {
        MyItemTouchHelperCallback callback = new MyItemTouchHelperCallback(mAdapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }


}



