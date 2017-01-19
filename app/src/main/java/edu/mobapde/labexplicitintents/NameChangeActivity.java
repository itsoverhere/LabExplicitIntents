package edu.mobapde.labexplicitintents;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NameChangeActivity extends AppCompatActivity {

    Button buttonDone;
    EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_change);

        buttonDone = (Button) findViewById(R.id.button_done);
        etName = (EditText) findViewById(R.id.et_name);

        if(getIntent().hasExtra(MainActivity.EXTRA_NAME)){
            etName.setText(getIntent().getStringExtra(MainActivity.EXTRA_NAME));
        }

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                if(name.isEmpty()){
                    Snackbar.make(buttonDone, "Name cannot be empty.", Snackbar.LENGTH_SHORT).show();
                }else {
                    Intent data = new Intent();
                    data.putExtra(MainActivity.EXTRA_NAME, name);
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });
    }
}
