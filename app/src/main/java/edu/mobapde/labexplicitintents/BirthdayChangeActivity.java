package edu.mobapde.labexplicitintents;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import java.util.Calendar;

public class BirthdayChangeActivity extends AppCompatActivity {

    CalendarView cvBirthday;
    Button buttonDone;
    Calendar selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday_change);

        buttonDone = (Button) findViewById(R.id.button_done);
        cvBirthday = (CalendarView) findViewById(R.id.cal_birthday);

        long previousDateInMillis = getIntent().getLongExtra(MainActivity.EXTRA_BIRTHDAY,
                Calendar.getInstance().getTimeInMillis());
        selectedDate = Calendar.getInstance();
        selectedDate.setTimeInMillis(previousDateInMillis);

        if(getIntent().hasExtra(MainActivity.EXTRA_BIRTHDAY)){
            cvBirthday.setDate(previousDateInMillis);
        }else{
            cvBirthday.setDate(Calendar.getInstance().getTimeInMillis());
        }
        
        cvBirthday.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate.set(year, month, dayOfMonth);
            }
        });

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra(MainActivity.EXTRA_BIRTHDAY, selectedDate.getTimeInMillis());
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
