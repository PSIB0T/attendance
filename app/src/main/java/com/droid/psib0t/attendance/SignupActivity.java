package com.droid.psib0t.attendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}
