package com.gaoyuan.materialdesign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.PopupMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.gaoyuan.materialdesign.recycler.RecyclerViewActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> mList;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (LinearLayout) findViewById(R.id.ll);
        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        ListView listView = (ListView) findViewById(R.id.listview);
        MyAdapter adapter = new MyAdapter();
        listView.setAdapter(adapter);

    }

    public void showPop(View view) {
        ListPopupWindow listPopupWindow = new ListPopupWindow(this);
        listPopupWindow.setAdapter(new MyAdapter());
        //设置锚点 pop弹出的位置相对于view的位置
        listPopupWindow.setAnchorView(view);
        listPopupWindow.setWidth(400);
        listPopupWindow.setHeight(400);
        listPopupWindow.show();
    }

    public void showPopMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this,view);
        getMenuInflater().inflate(R.menu.pop_menu,popupMenu.getMenu());
        popupMenu.show();

    }

    public void toRecycler(View view) {
        startActivity(new Intent(this,RecyclerViewActivity.class));
    }


    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(MainActivity.this, R.layout.item_listview, null);

            return convertView;
        }
    }

}
