package com.gaoyuan.materialdesign;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;


/**
 * 沉浸式设计
 */
public class TranslucentActivity extends BaseTranslucentActivity {
    ImageView mImageView;

    @Override
    public int getContentViewId() {
        return R.layout.activity_translucent;
    }

    @Override
    public void init() {
        mImageView = (ImageView) findViewById(R.id.img_select);
        findViewById(R.id.bt_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTranslucentPrimaryColor(R.color.black);
            }
        });

        findViewById(R.id.bt_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MultiImageSelector.create()
                        .single()
                        .start(TranslucentActivity.this, 110);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 110) {
            if (resultCode == RESULT_OK) {
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                mImageView.setImageURI(Uri.parse(path.get(0)));
                changeTitleBarColorOfPlaette();
            }
        }
    }


    private void changeTitleBarColorOfPlaette() {

        BitmapDrawable drawable = (BitmapDrawable) mImageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                //暗、柔和的颜色
                int darkMutedColor = palette.getDarkMutedColor(getResources().getColor(R.color.title_bar_color));//如果分析不出来，则返回默认颜色
                //暗、柔和
                int lightMutedColor = palette.getLightMutedColor(getResources().getColor(R.color.title_bar_color));
                //暗、鲜艳
                int darkVibrantColor = palette.getDarkVibrantColor(getResources().getColor(R.color.title_bar_color));
                //亮、鲜艳
                int lightVibrantColor = palette.getLightVibrantColor(getResources().getColor(R.color.title_bar_color));
                //柔和
                int mutedColor = palette.getMutedColor(getResources().getColor(R.color.title_bar_color));
                //柔和
                int vibrantColor = palette.getVibrantColor(getResources().getColor(R.color.title_bar_color));
                Palette.Swatch lightVibrantSwatch = palette.getVibrantSwatch();
                // if (lightVibrantSwatch != null) {
                //    int bodyTextColor = lightVibrantSwatch.getBodyTextColor();
                //    int titleTextColor = lightVibrantSwatch.getTitleTextColor();
                //    Log.e("gy", "bodyTextColor" + bodyTextColor);
                //    setTranslucentPrimaryColor(bodyTextColor);
                //    return;
                //  }

                Log.e("gy", "titleTextColor" + lightVibrantColor);
                setTranslucentPrimaryColor(lightVibrantColor);
            }
        });
    }


}