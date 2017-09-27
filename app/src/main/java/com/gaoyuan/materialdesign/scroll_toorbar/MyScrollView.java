package com.gaoyuan.materialdesign.scroll_toorbar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {

	private TranslucentListener listener;
	
	
	public void setTranslucentListener(TranslucentListener listener) {
		this.listener = listener;
	}

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if(listener!=null){
			int scrollY = getScrollY();
			int screen_height = getContext().getResources().getDisplayMetrics().heightPixels;
			if(scrollY<=screen_height/3f){//超过屏幕的1／3就不计算
				listener.onTranlucent(1-scrollY/(screen_height/3f));
			}
		}
	}
	
}
