package com.example.mostimportant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class Settings extends AppCompatActivity {

    Switch aSwitch;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        aSwitch = (Switch) findViewById(R.id.switch1);
        btn = (Button) findViewById(R.id.button4);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResetData.class);
                startActivity(intent);
            }
        });

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, age INTEGER, weight REAL, height REAL)");
        Cursor account = db.rawQuery("SELECT * FROM users;", null);
        if (!account.moveToFirst()) {
            Intent intent = new Intent(getApplicationContext(), input_name.class);
            db.close();
            startActivity(intent);
        }
        db.execSQL("CREATE TABLE IF NOT EXISTS settings (name TEXT, val TEXT)");
        Cursor query = db.rawQuery("SELECT * FROM settings;", null);
        if(!query.moveToFirst()){
            db.execSQL("INSERT INTO settings " +
                    "VALUES (\"notifications\", \"1\")");
            aSwitch.setChecked(true);
            query.moveToLast();
        } else {
            query.moveToLast();
            if (query.getString(query.getColumnIndex("val")).equals("1")) {
                aSwitch.setChecked(true);
            } else {
                aSwitch.setChecked(false);
            }
        }
        db.close();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        aSwitch = (Switch) findViewById(R.id.switch1);

        boolean f = aSwitch.isChecked();

        if (f) {
            db.execSQL("INSERT INTO settings " +
                    "VALUES (\"notifications\", \"1\")");
        } else {
            db.execSQL("INSERT INTO settings " +
                    "VALUES (\"notifications\", \"0\")");
        }
        db.close();
    }
}