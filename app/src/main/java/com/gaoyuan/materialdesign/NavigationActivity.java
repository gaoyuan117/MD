package com.gaoyuan.materialdesign;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.gaoyuan.materialdesign.R;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String content;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        mNavigationView = (NavigationView) findViewById(R.id.navigation);
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mNavigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_gallery:
                content = "相册";
                break;
            case R.id.action_details:
                content = "详情";
                break;
            case R.id.action_about:
                content = "关于";
                break;
            case R.id.action_play:
                content = "播放";
                break;
            case R.id.action_pause:
                content = "暂停";
                break;
        }
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
        mDrawerlayout.closeDrawers();
        return false;
    }
}
