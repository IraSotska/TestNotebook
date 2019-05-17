package com.example.zver.notebook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteDetailsActivity extends AppCompatActivity {

    DBHelper db;
    EditText n_text;
    String date, text;
    public static SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DBHelper(getApplicationContext());
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);

        n_text = (EditText) findViewById(R.id.text);

        Button clickButton = (Button) findViewById(R.id.clickButton);
        clickButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH.mm");
                Date date1 = new Date();
                date=dateFormat.format(date1).toString();
                text = n_text.getText().toString();

                if (text.length() == 0) {
                    Toast.makeText(getApplicationContext(), "TEXT BOX IS EMPTY!!!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    db.insertPerson(date, text);
                    Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        n_text = (EditText) findViewById(R.id.text);
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH.mm");
        Date date1 = new Date();
        date=dateFormat.format(date1).toString();
        text = n_text.getText().toString();
        db.insertPerson(date, text);

        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}