package com.gaoyuan.materialdesign.recycler.divider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by gaoyuan on 2017/8/17.
 */

public class RecyclerViewGridDivider extends RecyclerView.ItemDecoration {
    private Context context;
    private int mDividerHeight;
    private Drawable mDivider;
    private Paint mPaint;


    public RecyclerViewGridDivider(Context context, int dividerHeignt, int dividerColor) {
        this.context = context;
        mDividerHeight = dividerHeignt;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(dividerColor);
        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //2.调用该方法绘制
        //RecyclerView会回掉绘制方法，需要自己绘制分割线
        drawVertical(c, parent);
        drawHorizontal(c, parent);
        super.onDraw(c, parent, state);
    }

    /**
     * 画垂直线
     *
     * @param c
     * @param parent
     */
    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getChildCount();
        int top = 0;
        int left = 0;
        int right = 0;
        int bottom = 0;
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            if (mDivider != null) {
                left = child.getRight() + params.rightMargin;
                right = left + mDividerHeight;
                bottom = child.getBottom() + params.bottomMargin;
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }

            if (mPaint != null) {
                left = child.getRight() + params.rightMargin;
                if ((i + 1) % spanCount == 0) {
                    right = 0;
                } else {
                    right = left + mDividerHeight;
                }

                top = child.getTop() - params.rightMargin;
                bottom = child.getBottom() + params.bottomMargin;

                Log.e("gy", "top" + top);
                Log.e("gy", "bottom" + bottom);
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
        int childCount = parent.getChildCount();
        int spanCount = getSpanCount(parent);
        int bottom = 0;
        for (int i = 0; i < childCount; i++) {

            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            if (mDivider != null) {
                int left = child.getLeft() - params.leftMargin + Math.round(ViewCompat.getTranslationX(child));
                int right = child.getRight() + params.rightMargin;
                int top = child.getBottom() + params.bottomMargin;
                bottom = top + mDividerHeight;
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }

            if (mPaint != null) {
                int left = child.getLeft() - params.leftMargin;
                int right = child.getRight() + params.rightMargin + mDividerHeight;
                int top = child.getBottom() + params.bottomMargin;
                int i1 = childCount % spanCount;

                //判断最后一行，分两种情况
                if (i1 == 0) {
                    if (i >= childCount - spanCount) {
                        bottom = 0;
                    } else {
                        bottom = top + mDividerHeight;
                    }
                } else if (i >= childCount - i1) {//最后一行
                    bottom = 0;
                } else {
                    bottom = top + mDividerHeight;
                }
                Log.e("gy", "left" + left);
                Log.e("gy", "right" + right);
                c.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //1.调用该方法，获得条目的偏移量(所有的条目都会调用一次这个方法)
        //首先会先获取条目之间的间隙宽度--Rect矩形区域

        int childPosition = parent.getChildAdapterPosition(view);
        int right = mDividerHeight;
        int bottom = mDividerHeight;
//        if (isLastColum(childPosition, parent)) {//是否是最后一列
//            right = 0;
//        }
//        if (isLastRow(childPosition, parent)) {//是否是最后一列
//            bottom = 0;
//        }
        outRect.set(0, 0, right, bottom);
    }

    /**
     * 是否是最后一行
     *
     * @param childPosition
     * @param parent
     * @return
     */
    private boolean isLastRow(int childPosition, RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        int itemCount = parent.getAdapter().getItemCount();

        int count = itemCount % spanCount;

        if (count == 0 || count < spanCount) {//最后一行
            return true;
        }
        return false;
    }

    /**
     * 是否是最后一列
     *
     * @param childPosition
     * @param parent
     * @return
     */
    private boolean isLastColum(int childPosition, RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        if ((childPosition + 1) % spanCount == 0) {
            return true;
        }

        return false;
    }


    private int getSpanCount(RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager lm = (GridLayoutManager) parent.getLayoutManager();
            int spanCount = lm.getSpanCount();
            return spanCount;
        }
        return 1;
    }


}
