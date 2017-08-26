package com.gaoyuan.materialdesign.recycler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by gaoyuan on 2017/8/17.
 *
 */

public class RecyclerViewDivider extends RecyclerView.ItemDecoration {
    private Context context;
    private int mOrientation = LinearLayoutManager.VERTICAL;
    private int mDividerHeight;
    private Drawable mDivider;
    private Paint mPaint;



    public RecyclerViewDivider(Context context, int mOrientation, int dividerHeignt, int dividerColor) {
        this.context = context;
        this.mOrientation = mOrientation;
        mDividerHeight = dividerHeignt;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(dividerColor);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //2.调用该方法绘制
        //RecyclerView会回掉绘制方法，需要自己绘制分割线
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent);
        } else if (mOrientation == LinearLayoutManager.HORIZONTAL) {
            drawHorizontal(c, parent);
        }
        super.onDraw(c, parent, state);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {

            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            if (mDivider != null) {
                int left = child.getRight() + params.rightMargin + Math.round(ViewCompat.getTranslationX(child));
                int right = left + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }

            if (mPaint != null) {
                int left = child.getRight() + params.rightMargin + Math.round(ViewCompat.getTranslationX(child));
                int right = left + mDividerHeight;
                c.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    /**
     * 画水平线
     *
     * @param c
     * @param parent
     */
    private void drawVertical(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getMeasuredWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {

            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            if (mDivider != null) {
                int top = child.getBottom() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
                int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }

            if (mPaint != null) {
                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDividerHeight;
                c.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //1.调用该方法，获得条目的偏移量(所有的条目都会调用一次这个方法)
        //首先会先获取条目之间的间隙宽度--Rect矩形区域

        if (mOrientation == LinearLayoutManager.VERTICAL) {
            outRect.set(0, 0, 0, mDividerHeight);
        } else if (mOrientation == LinearLayoutManager.HORIZONTAL) {
            outRect.set(0, 0, mDividerHeight, 0);
        }

        super.getItemOffsets(outRect, view, parent, state);
    }

    public void setOrientation(int orientation) {
        if (orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL) {
            throw new IllegalArgumentException("请确认方向");
        }

        this.mOrientation = orientation;
    }


}
