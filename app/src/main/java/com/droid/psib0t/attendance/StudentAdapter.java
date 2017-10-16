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

public class StudentAdapter extends BaseAdapter{

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Student> dataSource;

    StudentAdapter(Context context, ArrayList<Student> items){
        mContext = context;
        dataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return dataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = mInflater.inflate(R.layout.list_item_student, viewGroup, false);

        TextView titleTextView = (TextView) rowView.findViewById(R.id.student_list_title);
        TextView subTextView = (TextView) rowView.findViewById(R.id.student_list_subtitle);
        TextView studentAttendance = (TextView) rowView.findViewById(R.id.studentAttendance);
        Student item = (Student) this.getItem(i);

        titleTextView.setText(item.firstName + " " + item.lastName);
        subTextView.setText(item.rollNo + "");
        studentAttendance.setText(item.presentDates.size() + "");

        return rowView;
    }
}
