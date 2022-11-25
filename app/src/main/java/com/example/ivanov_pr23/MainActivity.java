package com.example.ivanov_pr23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edittext;
    EditText edittext2;
    TextView textView;
    float x1, x2, y1, y2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext = findViewById(R.id.editTextTextPersonName);
        edittext2 = findViewById(R.id.editTextTextPersonName2);
        textView = findViewById(R.id.textView2);
        button = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                textView.setText("Здесь могла быть ваша реклама");

                String name1 = edittext.getText().toString();
                int age1 = Integer.parseInt(edittext2.getText().toString());

                SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);

                ContentValues values = new ContentValues();

                db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, age INTEGER, UNIQUE(name))");
                db.execSQL("INSERT OR IGNORE INTO users VALUES ( '"+name1+"', "+age1+");");
                //values.put(name, age2);
                db.close();
                textView.setText("Запись добавлена");
            }
        });
    }

    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchevent.getX();
                y2 = touchevent.getY();
                if (x1 < x2) {
                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
/*
    button.setOnClickListener(new View.OnClickListener()
    public void onClick(View view){
        textView.setText("Здесь могла быть ваша реклама");

        String name1 = edittext.getText().toString();
        int age1 = Integer.parseInt(edittext2.getText().toString());

        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);

        ContentValues values = new ContentValues();

        db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, age INTEGER, UNIQUE(name))");
        db.execSQL("INSERT OR IGNORE INTO users VALUES ( "+name1+", "+age1+");");
        //values.put(name, age2);
        db.close();
        textView.setText("Запись добавлена");
    }*/
}