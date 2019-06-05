package com.example.asus.quanlysach;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class dangki extends AppCompatActivity {

    EditText edtname,edtpassword,edtconfrim;
    Button btndki;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);

        db = new DatabaseHelper(this);
        edtname = (EditText)findViewById(R.id.registername);
        edtpassword = (EditText)findViewById(R.id.registerpassword);
        edtconfrim = (EditText)findViewById(R.id.registerconfirmpassword);
        btndki = (Button)findViewById(R.id.btnregister);
        btndki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = edtname.getText().toString();
                String s2 = edtpassword.getText().toString();
                String s3 = edtconfrim.getText().toString();
                if(s1.equals("")||s2.equals("")){
                    Toast.makeText(dangki.this, "Vui long nhap day du thong tin", Toast.LENGTH_SHORT).show();
                }
                else{
                   if(s2.equals(s3)){
                     Boolean ktrname = db.ktraname(s1);
                     if(ktrname==true){
                        Boolean insert = db.insert(s1,s2);
                        if(insert==true){
                            Toast.makeText(dangki.this, "Dang ki thanh cong", Toast.LENGTH_SHORT).show();
                        }
                     }
                     else{
                         Toast.makeText(dangki.this, "Tai khoan da ton tai", Toast.LENGTH_SHORT).show();
                     }
                   }
                }
            }
        });
    }
}
