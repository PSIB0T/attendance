package com.droid.psib0t.attendance;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by arvindo on 15/10/17.
 */

public class AttendanceAdapter extends ArrayAdapter {
    private ArrayList<StudentCheck> dataSet;
    Context mContext;

    public AttendanceAdapter (ArrayList<StudentCheck> data, Context context) {
        super(context, R.layout.list_item_attendance, data);
        this.dataSet = data;
        this.mContext = context;
    }

    private static class ViewHolder {
        TextView txtName;
        CheckBox checkBox;
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSet.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_attendance, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);

            result=convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }


        return  result;
    }
}
