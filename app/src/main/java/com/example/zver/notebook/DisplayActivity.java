package com.example.zver.notebook;

import android.content.Intent;
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

public class DisplayActivity extends AppCompatActivity {

    String date, text1;
    DBHelper db;
    EditText text_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        db=new DBHelper(getApplicationContext());

        final EditText textedit=(EditText)findViewById(R.id.text_edit);
        final String sub_id;

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sub_id = getIntent().getStringExtra("id");
        String title=getIntent().getStringExtra("title");
        String text=getIntent().getStringExtra("text");
        textedit.setText(text);

        Button clickButton = (Button) findViewById(R.id.clickButton);
        clickButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH.mm");
        Date date1 = new Date();
        date=dateFormat.format(date1).toString();
        text1 = textedit.getText().toString();

        if( text1.length() == 0){
              Toast.makeText(getApplicationContext(), "TEXT BOX IS EMPTY!!!",
              Toast.LENGTH_SHORT).show();
        }
        else {
              db.updatePerson(date,text1,sub_id);
              Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
              finish();}
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        final EditText textedit=(EditText)findViewById(R.id.text_edit);
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH.mm");
        Date date1 = new Date();
        date=dateFormat.format(date1).toString();
        text1 = textedit.getText().toString();
        String sub_id = getIntent().getStringExtra("id");
        db.updatePerson(date,text1,sub_id);

        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
