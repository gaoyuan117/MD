package com.gaoyuan.materialdesign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * 沉浸式设计
 */
public class TranslucentActivity extends BaseTranslucentActivity {

    @Override
    public int getContentViewId() {
        return R.layout.activity_translucent;
    }

    @Override
    public void init() {
        findViewById(R.id.bt_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTranslucentPrimaryColor(R.color.black);
            }
        });
    }

}
