package com.example.mostimportant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResetData extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_data);

        editText1 = (EditText) findViewById(R.id.editText3);
        editText2 = (EditText) findViewById(R.id.editText4);
        editText3 = (EditText) findViewById(R.id.editText5);
        editText4 = (EditText) findViewById(R.id.editTextTextPersonName2);

        btn = (Button) findViewById(R.id.button);

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, age INTEGER, weight REAL, height REAL)");
        Cursor account = db.rawQuery("SELECT * FROM users;", null);
        if (!account.moveToFirst()) {
            Intent intent = new Intent(getApplicationContext(), input_name.class);
            db.close();
            startActivity(intent);
        }

        account.moveToLast();
        editText1.setText(account.getString(account.getColumnIndex("weight")).toString());
        editText2.setText(account.getString(account.getColumnIndex("height")).toString());
        editText3.setText(account.getString(account.getColumnIndex("age")).toString());
        editText4.setText(account.getString(account.getColumnIndex("name")).toString());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
                String name = editText4.getText().toString();
                int age = Integer.parseInt(editText3.getText().toString());
                double weight = Double.parseDouble(editText1.getText().toString());
                double height = Double.parseDouble(editText2.getText().toString());
                db.execSQL("INSERT INTO users(name, age, weight, height) VALUES " +
                        "('"+ name + "', " + age + ", "+ weight + ", "+ height + ")");
                Toast.makeText(getApplicationContext(), "Данные обновлены!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                db.close();
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        String name = editText4.getText().toString();
        int age = Integer.parseInt(editText3.getText().toString());
        double weight = Double.parseDouble(editText1.getText().toString());
        double height = Double.parseDouble(editText2.getText().toString());
    }
}