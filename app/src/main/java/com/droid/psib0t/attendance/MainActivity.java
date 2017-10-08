package com.droid.psib0t.attendance;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    int year, month, day;
    static final int DIALOG_ID = 0;

    Calendar calendar;
    TextView dateText;

    Button datePickerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        dateText = (TextView) findViewById(R.id.dateText);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePickerButton = (Button) findViewById(R.id.datePickerButton);

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        year = i;
                        month = i1;
                        day = i2;

                        dateText.setText("ggwp  ");
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }


}
