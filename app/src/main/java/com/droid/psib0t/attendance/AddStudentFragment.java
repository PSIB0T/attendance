package com.droid.psib0t.attendance;


import android.os.Bundle;
import android.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


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
                DatabaseReference tempReference = ((MainActivity) getActivity()).studentReference;
                String key = tempReference.push().getKey();
                String fname = firstName.getText().toString();
                String lname =  lastName.getText().toString();
                String rNo = rollNo.getText().toString();
                if(fname.isEmpty() || lname.isEmpty() || rNo.isEmpty()){
                    Toast.makeText(getActivity(), "One or more fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                Student student = new Student(fname, lname, Integer.parseInt(rNo));

                FirebaseUser user = ((MainActivity) getActivity()).firebaseAuth.getCurrentUser();

                tempReference.child(key).setValue(student, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if(databaseError != null) {
                            Toast.makeText(getActivity(), "Error saving data", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), "Data saved successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        return view;
    }

}
