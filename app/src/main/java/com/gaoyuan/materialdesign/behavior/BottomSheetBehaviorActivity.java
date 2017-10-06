package com.gaoyuan.materialdesign.behavior;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gaoyuan.materialdesign.R;

public class BottomSheetBehaviorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_behavior);
        View shareView = findViewById(R.id.share_view);
        //获取BottomSheetBehavior
        final BottomSheetBehavior sheetBehavior = BottomSheetBehavior.from(shareView);
        //设置折叠时的高度
        //sheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);
        //监听BottomSheetBehavior 状态的变化
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        //下滑的时候是否可以隐藏
        sheetBehavior.setHideable(true);

        /**
         *
         * STATE_EXPANDED 展开状态，显示完整布局。
         * STATE_COLLAPSED 折叠状态，显示peekHeigth 的高度，如果peekHeight为0，则全部隐藏,与STATE_HIDDEN效果一样。
         * STATE_DRAGGING 拖拽时的状态
         * STATE_HIDDEN 隐藏时的状态
         * STATE_SETTLING 释放时的状态
         *
         **/
        findViewById(R.id.btn_show_bottom_sheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }
        });
    }


}
