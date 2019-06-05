package com.example.asus.quanlysach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText edt1,edt2;
    Button btnlogin,btndki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        edt1 = (EditText)findViewById(R.id.name);
        edt2 = (EditText)findViewById(R.id.password);
        btnlogin = (Button)findViewById(R.id.login);
        btndki = (Button)findViewById(R.id.dki);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt1.getText().toString();
                String password = edt2.getText().toString();
                Boolean login = db.login(name,password);
                if(login==true){
                     Intent sach = new Intent(MainActivity.this,book.class);
                     startActivity(sach);
                }else{
                    Toast.makeText(MainActivity.this, "Wrong Name or Password ", Toast.LENGTH_SHORT).show();
                }


            }
        });
        btndki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dki = new Intent(MainActivity.this,dangki.class);
                startActivity(dki);
            }
        });
    }
}
