package com.gaoyuan.materialdesign;

import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.gaoyuan.materialdesign.R;

public class DrawerLayoutActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_menu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //将actionBar替换成toolbar
        setSupportActionBar(toolbar);

        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        //同步状态
        drawerToggle.syncState();
        //给侧滑控件设置监听
//		drawerlayout.setDrawerListener(drawerToggle);
        drawerlayout.setScrimColor(Color.TRANSPARENT);//设置背景不变暗
        drawerlayout.setDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerStateChanged(int newState) {
                // 状态发生改变

            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                // 滑动的过程当中不断地回调 slideOffset：0~1
                View content = drawerlayout.getChildAt(0);
                View menu = drawerView;
                float scale = 1 - slideOffset;//1~0
                float leftScale = (float) (1 - 0.3 * scale);
                Log.e("gy", "leftScale：" + leftScale);
                float rightScale = (float) (0.7f + 0.3 * scale);//0.7~1
                menu.setScaleX(leftScale);//1~0.7
                menu.setScaleY(leftScale);//1~0.7
                content.setScaleX(rightScale);
                content.setScaleY(rightScale);
                menu.setTranslationX(menu.getMeasuredWidth() * slideOffset);//0~width

//                menu.setTranslationX(20);//0~width
//                menu.setTranslationY(200);//0~width

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // 打开

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                // 关闭

            }
        });
    }
}
