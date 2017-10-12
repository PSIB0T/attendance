package com.droid.psib0t.attendance;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddStudentFragment extends Fragment {

    EditText firstName, lastName, rollNo;

    Button addStudent;

    public AddStudentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_add_student, container, false);
        firstName = (EditText) view.findViewById(R.id.firstName);
        lastName = (EditText) view.findViewById(R.id.lastName);
        rollNo = (EditText) view.findViewById(R.id.rollNo);

        addStudent = (Button) view.findViewById(R.id.btn_addStudent);

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference tempReference = ((MainActivity) getActivity()).databaseReference;
                String key = tempReference.push().getKey();

                Student student = new Student(firstName.getText().toString(), lastName.getText().toString(), Integer.parseInt(rollNo.getText().toString()));

                FirebaseUser user = ((MainActivity) getActivity()).firebaseAuth.getCurrentUser();

                tempReference.child(key).setValue(student);

            }
        });

        return view;
    }

}
