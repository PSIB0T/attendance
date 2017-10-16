package com.droid.psib0t.attendance;

import java.util.ArrayList;

/**
 * Created by arvindo on 15/10/17.
 */

public class StudentCheck extends Student{
    public boolean isChecked = false;

    StudentCheck() {
        this.presentDates = new ArrayList<Long>();
    }
}
