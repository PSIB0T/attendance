package com.droid.psib0t.attendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by arvindo on 12/10/17.
 */

public class StudentAdapter<T> extends BaseAdapter{

    private Context mContext;
    private LayoutInflater mInflater;
    private T[] dataSource;

    StudentAdapter(Context context, T[] items){
        mContext = context;
        dataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return dataSource.length;
    }

    @Override
    public Object getItem(int i) {
        return dataSource[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = mInflater.inflate(R.layout.list_item_student, viewGroup, false);

        TextView titleTextView = (TextView) rowView.findViewById(R.id.recipe_list_title);

        T item = (T) this.getItem(i);

        titleTextView.setText(item.toString());

        return rowView;
    }
}
