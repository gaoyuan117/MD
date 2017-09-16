package com.gaoyuan.materialdesign;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gaoyuan.materialdesign.R;

public class SnackBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar);
    }

    /**
     * 自定义Toast
     * @param v
     */
    public void showCustomToast(View v){
        Toast toast = new Toast(this);
        LayoutInflater inflate = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflate.inflate(R.layout.custom_toast, null);
        TextView tv = (TextView)view.findViewById(R.id.textView1);
        tv.setText("自定义吐司在此！");

//        toast.setGravity(gravity, xOffset, yOffset)
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();

    }


    public void showSnackbar(View v){
        //LENGTH_INDEFINITE:无穷
        Snackbar snackbar = Snackbar.make(v, "是否开启加速模式？", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("确定", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showCustomToast(null);
            }
        });
        //不能设置多个action，会被覆盖
        snackbar.setAction("取消", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showCustomToast(null);
            }
        });
        snackbar.setCallback(new Snackbar.Callback() {

            @Override
            public void onDismissed(Snackbar snackbar, int event) {//消失
                Log.e("gy","onDismissed");
                showCustomToast(null);
                super.onDismissed(snackbar, event);
            }

            @Override
            public void onShown(Snackbar snackbar) {//显示中
                Log.e("gy","onShown");
                super.onShown(snackbar);
            }
        });
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();

    }
}
