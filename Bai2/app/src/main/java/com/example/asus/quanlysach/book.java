package com.example.asus.quanlysach;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class book extends AppCompatActivity {
    EditText edtName, edtMaSv, edtLop;
    Button btnThem, btnSua, btnXoa, btnXemchitiet, btnXemtatca;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        edtName = (EditText)findViewById(R.id.editName);
        edtMaSv = (EditText)findViewById(R.id.editMasv);
        edtLop = (EditText)findViewById(R.id.editClass);
        btnThem = (Button) findViewById(R.id.btnThem);
        btnSua = (Button) findViewById(R.id.btnSua);
        btnXoa = (Button) findViewById(R.id.btnXoa);
        btnXemchitiet= (Button) findViewById(R.id.btnxemchitiet);
        btnXemtatca = (Button) findViewById(R.id.btnxemtatca);

        db = openOrCreateDatabase("studentsdb",Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS students(name VARCHAR, masv VARCHAR, lop VARCHAR);");

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtName.getText().toString().trim().length()==0||
                        edtMaSv.getText().toString().trim().length()==0||
                        edtLop.getText().toString().trim().length()==0)
                {
                    showMessage("Error","Vui long dien day du thong tin");
                    return;
                }
                db.execSQL("INSERT INTO students VALUES ('"+edtName.getText()+"','"+edtMaSv.getText()+"','"+edtLop.getText()+"');");
                showMessage("Success", "Them thanh cong");
                ClearText();

            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtMaSv.getText().toString().trim().length()==0){
                    showMessage("Error","Ma Sach khong hop le");
                    return;
                }
                Cursor c = db.rawQuery("SELECT * FROM students WHERE masv='"+edtMaSv.getText()+"'",null);
                if(c.moveToFirst()){
                    db.execSQL("DELETE FROM students WHERE masv='"+edtMaSv.getText()+"'");
                    showMessage("Sucess","Xoa thanh cong");
                }
                else{
                    showMessage("Error","Ma Sach khong hop le");
                }
                ClearText();

            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtMaSv.getText().toString().trim().length()==0){
                    showMessage("Error","Ma Sach khong hop le");
                    return;
                }
                Cursor c = db.rawQuery("SELECT * FROM students WHERE masv='"+edtMaSv.getText()+"'",null);
                if(c.moveToFirst()){
                    db.execSQL("UPDATE students SET name='"+edtName.getText()+"',lop='"+edtLop.getText()+"' WHERE masv='"+edtMaSv.getText()+"'");
                    showMessage("Sucess","Sua thanh cong");
                }
                else{
                    showMessage("Error","Ma Sach khong hop le");
                }
                ClearText();

            }
        });
        btnXemchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtMaSv.getText().toString().trim().length()==0)
                {
                    showMessage("Error","Ma Sach khong hop le");
                    return;
                }
                Cursor c = db.rawQuery("SELECT * FROM students WHERE masv='"+edtMaSv.getText()+"'",null);
                if(c.moveToFirst())
                {
                    edtName.setText(c.getString(0));
                    edtLop.setText(c.getString(2));
                }
                else
                {
                    showMessage("Error","Ma Sach khong hop le");
                    ClearText();
                }


            }
        });
        btnXemtatca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor c = db.rawQuery("SELECT * FROM students",null);
                if(c.getCount()==0){
                    showMessage("Error","Khong tim thay sach");
                }
                StringBuffer buffer = new StringBuffer();
                while(c.moveToNext()){
                    buffer.append("Name: "+c.getString(0)+"\n");
                    buffer.append("Ma Sach: "+c.getString(1)+"\n");
                    buffer.append("Category: "+c.getString(2)+"\n");
                }
                showMessage("Books Details:",buffer.toString());

            }
        });
    }
    private void ClearText() {
        edtName.setText("");
        edtMaSv.setText("");
        edtLop.setText("");
        edtMaSv.requestFocus();
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
