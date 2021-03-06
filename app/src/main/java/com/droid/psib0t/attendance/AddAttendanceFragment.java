package com.droid.psib0t.attendance;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddAttendanceFragment extends Fragment {


    int year, month, day;

    Calendar calendar;

    Button datePickerButton, addAttendance;
    ChildEventListener listener;
    Switch checkOrUncheck;
    ProgressBar attendanceProgress;

    ListView listView;
    private ArrayList<StudentCheck> students;
    DatabaseReference tempReference;

    public AddAttendanceFragment() {
        // Required empty public constructor
        students = new ArrayList<StudentCheck>();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_attendance, container, false);
        tempReference = ((MainActivity) getActivity()).studentReference;
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerButton = (Button) view.findViewById(R.id.datePickerButton);
        checkOrUncheck = (Switch) view.findViewById(R.id.checkOrUncheck);
        addAttendance = (Button) view.findViewById(R.id.submitAttendance);
        attendanceProgress = (ProgressBar) view.findViewById(R.id.saveAttendanceProgress);
        attendanceProgress.setVisibility(View.GONE);

        checkOrUncheck.setChecked(true);

        datePickerButton.setText(day + "/" + month + "/" + year);

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        year = i;
                        month = i1;
                        day = i2;
                        calendar.set(i, i1, i2);
                        datePickerButton.setText(day + "/" + month + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        addAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attendanceProgress.setVisibility(View.VISIBLE);
                long time = calendar.getTimeInMillis();
                for(StudentCheck student: students) {
                    Log.d("isChecked", student.firstName + " " +  student.isChecked + "");
                    if(student.isChecked){
                        student.addDate(time);
                        tempReference.child(student.id).setValue(student);
                        Log.d("ggwp", "Attendance checked!");
                    }
                }
                attendanceProgress.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Data saved successfully", Toast.LENGTH_SHORT).show();
            }
        });

        listView = (ListView) view.findViewById(R.id.attendanceListView);

        final AttendanceAdapter adapter = new AttendanceAdapter(students, getActivity());

        listView.setAdapter(adapter);

        listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                StudentCheck student = dataSnapshot.getValue(StudentCheck.class);
                students.add(student);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        checkOrUncheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                for(StudentCheck student: students) {
                    student.isChecked = isChecked;
                }
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        tempReference.removeEventListener(listener);
        Log.d("ggwp", "pause");
    }

    @Override
    public void onResume() {
        super.onResume();
        tempReference.addChildEventListener(listener);
        Log.d("ggwp", "resume");
    }

}