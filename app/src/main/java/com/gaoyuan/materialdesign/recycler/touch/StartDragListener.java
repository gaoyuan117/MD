package com.gaoyuan.materialdesign.recycler.touch;

import android.support.v7.widget.RecyclerView.ViewHolder;

public interface StartDragListener {
	/**
	 * 该接口用于需要主动回调拖拽效果的
	 * @param viewHolder
	 */
	public void onStartDrag(ViewHolder viewHolder);

}
