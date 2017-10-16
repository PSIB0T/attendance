package com.droid.psib0t.attendance;

import java.util.ArrayList;

/**
 * Created by arvindo on 12/10/17.
 */

public class Student {
    public String firstName;
    public String lastName;
    public String id;
    public int rollNo;
    ArrayList<Long> presentDates;

    Student(){

    }

    Student(String firstName, String lastName, int rollNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rollNo = rollNo;
        this.presentDates = new ArrayList<Long>();
    }

    public void addDate(long date){
        this.presentDates.add(new Long(date));
    }
}
