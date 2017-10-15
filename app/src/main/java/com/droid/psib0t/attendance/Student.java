package com.droid.psib0t.attendance;

/**
 * Created by arvindo on 12/10/17.
 */

public class Student {
    public String firstName;
    public String lastName;
    public int rollNo;

    Student(){

    }

    Student(String firstName, String lastName, int rollNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rollNo = rollNo;
    }
}
