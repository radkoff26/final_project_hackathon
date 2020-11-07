package com.example.mostimportant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.zip.Inflater;

public class input_data extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    EditText editText3;

    RadioGroup radioGroup;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        Intent intent = getIntent();

        editText1 = (EditText) findViewById(R.id.editText3);
        editText2 = (EditText) findViewById(R.id.editText4);
        editText3 = (EditText) findViewById(R.id.editText5);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean condition = (!editText1.getText().toString().equals("") && !editText1.getText().toString().equals(" ")
                        && !editText2.getText().toString().equals("") && !editText2.getText().toString().equals(" ")
                        && !editText3.getText().toString().equals("") && !editText3.getText().toString().equals(" "));

                condition = condition && (radioGroup.getCheckedRadioButtonId() != -1);

                if (condition) {
                    String name = intent.getStringExtra("msg");
                    if (name.equals("") || name.equals(" ")) {
                        name = "Unknown";
                    }
                    double weight = Double.parseDouble(editText1.getText().toString());
                    double height = Double.parseDouble(editText2.getText().toString());
                    int age = Integer.parseInt(editText3.getText().toString());
                    SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
                    db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, age INTEGER, weight REAL, height REAL)");
                    db.execSQL("INSERT INTO users " +
                            "VALUES ('" + name + "', " + age + ", " + weight + ", " + height + ")");
                    db.close();
                }
                Intent intent_out = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent_out);
            }
        });
    }
}