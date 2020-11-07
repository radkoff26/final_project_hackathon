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

public class input_name extends AppCompatActivity {

    Button cont;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_name);

        cont = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editTextTextPersonName);

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, age INTEGER, weight REAL, height REAL)");
        Cursor account = db.rawQuery("SELECT * FROM users;", null);
        if (account.moveToFirst()) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), input_data.class);
                String name = editText.getText().toString();
                if (name.equals("") || name.equals(" ")) {
                    Toast.makeText(getApplicationContext(), "Вы не ввели имя!", Toast.LENGTH_SHORT).show();
                } else {
                    intent.putExtra("msg", name);
                    startActivity(intent);
                };
            }
        });
    }


}