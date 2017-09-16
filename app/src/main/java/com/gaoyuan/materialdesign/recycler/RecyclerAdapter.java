package com.gaoyuan.materialdesign.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gaoyuan.materialdesign.R;
import com.gaoyuan.materialdesign.recycler.touch.ItemTouchMoveListener;
import com.gaoyuan.materialdesign.recycler.touch.StartDragListener;

import java.util.Collections;
import java.util.List;

/**
 * Created by gaoyuan on 2017/8/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements ItemTouchMoveListener {

    private StartDragListener listener;
    private Context context;
    private List<String> mList;

    public RecyclerAdapter(Context context, List<String> mList, StartDragListener listener) {
        this.context = context;
        this.mList = mList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_listview, parent, false);
        ViewHolder holder = new ViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.textView.setText(mList.get(position));

        holder.textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    listener.onStartDrag(holder);//点击拖动与滑动删除冲突
                }

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public boolean onItemRemove(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
        return true;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
