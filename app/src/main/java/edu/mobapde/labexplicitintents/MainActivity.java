package edu.mobapde.labexplicitintents;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_IDNUMBER = "idnumber";
    public static final String EXTRA_BIRTHDAY = "birthday";

    public static final int REQUEST_NAME = 0;
    public static final int REQUEST_IDNUMBER = 1;
    public static final int REQUEST_BIRTHDAY = 2;

    ImageButton ibName, ibIdNumber, ibBirthday;
    TextView tvName, tvIdNumber, tvBirthday;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ibName = (ImageButton) findViewById(R.id.button_name);
        ibIdNumber = (ImageButton) findViewById(R.id.button_idnumber);
        ibBirthday = (ImageButton) findViewById(R.id.button_birthday);

        tvName = (TextView) findViewById(R.id.tv_name);
        tvIdNumber = (TextView) findViewById(R.id.tv_idnumber);
        tvBirthday = (TextView) findViewById(R.id.tv_birthday);

        ibName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), NameChangeActivity.class);
                intent.putExtra(EXTRA_NAME, tvName.getText());
                startActivityForResult(intent, REQUEST_NAME);
            }
        });

        ibIdNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), IdNumberChangeActivity.class);
                intent.putExtra(EXTRA_IDNUMBER, tvIdNumber.getText());
                startActivityForResult(intent, REQUEST_IDNUMBER);
            }
        });

        ibBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    long time = df.parse(tvBirthday.getText().toString()).getTime();

                    Intent intent = new Intent(getBaseContext(), BirthdayChangeActivity.class);
                    intent.putExtra(EXTRA_BIRTHDAY, time);

                    startActivityForResult(intent, REQUEST_BIRTHDAY);
                } catch (ParseException e) {
                    Snackbar.make(ibBirthday, "An error occurred.", Snackbar.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_NAME:
                    tvName.setText(data.getStringExtra(MainActivity.EXTRA_NAME));
                    break;
                case REQUEST_IDNUMBER:
                    tvIdNumber.setText(data.getStringExtra(MainActivity.EXTRA_IDNUMBER));
                    break;
                case REQUEST_BIRTHDAY:
                    Date date = new Date();
                    date.setTime(data.getLongExtra(MainActivity.EXTRA_BIRTHDAY,
                                 Calendar.getInstance().getTimeInMillis()));
                    tvBirthday.setText(df.format(date));
                    break;
            }
        }
    }
}
