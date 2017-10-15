package com.droid.psib0t.attendance;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentList extends Fragment {

    private ListView studentListView;
    private String[] studentNames;
    private ArrayList<Student> students;
    ChildEventListener listener;
    DatabaseReference tempReference;

    public StudentList() {
        // Required empty public constructor
        students = new ArrayList<Student>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_list, container, false);
        studentListView = view.findViewById(R.id.student_list);
        tempReference = ((MainActivity) getActivity()).studentReference;

        final StudentAdapter adapter = new StudentAdapter(getActivity(), students);

        listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Student student = dataSnapshot.getValue(Student.class);
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

        studentListView.setAdapter(adapter);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("ggwp", "ggwp");
    }
}
