package com.droid.psib0t.attendance;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentList extends Fragment {

    private ListView studentListView;
    private String[] studentNames;

    public StudentList() {
        // Required empty public constructor
        studentNames = new String[]{"Arvind Narayanan", "John Doe", "Lorem Ipsum"};
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_list, container, false);
        studentListView = view.findViewById(R.id.student_list);

        StudentAdapter adapter = new StudentAdapter<String>(getActivity(), studentNames);
        studentListView.setAdapter(adapter);
        return view;
    }

}
