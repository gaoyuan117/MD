package com.gaoyuan.materialdesign.fab;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gaoyuan.materialdesign.R;

public class FabActivity extends AppCompatActivity {
    private boolean reverse = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab);
    }

    public void rotate(View v) {
        float toDegree = reverse ? -45f : 0f;
        ObjectAnimator animator = ObjectAnimator
                .ofFloat(v, "rotation", 0.0f, toDegree)
                .setDuration(400);
        animator.start();
        reverse = !reverse;
    }
}
