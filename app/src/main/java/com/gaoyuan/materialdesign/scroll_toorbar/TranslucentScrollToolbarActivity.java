package com.gaoyuan.materialdesign.scroll_toorbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.gaoyuan.materialdesign.R;

/**
 * 随着ScrollView的滑动，ToolBar改变透明度
 */
public class TranslucentScrollToolbarActivity extends AppCompatActivity implements TranslucentListener {
    private Toolbar toolbar;
    private MyScrollView scv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translucent_scroll_toolbar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        scv = (MyScrollView) findViewById(R.id.scrollView);
        scv.setTranslucentListener(this);

    }


    @Override
    public void onTranlucent(float alpha) {
        toolbar.setAlpha(alpha);
    }
}
