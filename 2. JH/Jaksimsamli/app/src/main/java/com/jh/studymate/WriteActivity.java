package com.jh.studymate;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class WriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        findViewById(R.id.whiteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etSubject =(EditText)findViewById(R.id.whiteSubjectET);
                EditText etPessword =(EditText)findViewById(R.id.whitePasswordET);
                EditText etWriter =(EditText)findViewById(R.id.whiteWriterET);
                EditText etContent =(EditText)findViewById(R.id.whiteContentET);
                EditText etMail =(EditText)findViewById(R.id.whiteMailET);
                EditText etPhon =(EditText)findViewById(R.id.whitePhonET);

                String stretSubject = etSubject.getText().toString();
                String stretPessword = etPessword.getText().toString();
                String stretWriter = etWriter.getText().toString();
                String stretContent = etContent.getText().toString();
                String stretMail = etMail.getText().toString();
                String stretPhon = etPhon.getText().toString();


                SQLiteDatabase db = openOrCreateDatabase("board.db",MODE_PRIVATE,null);

                String sql ="insert into board(\n" +
                        "subject, writer, mail, phone ,password, content,hit,wdate)\n" +
                        " values('" + stretSubject+ "','" + stretWriter+"','"+ stretMail +"','"+ stretPhon +"','"+stretPessword +"','"+stretContent +"',0,datetime('now','localtime'))";
                db.execSQL(sql);

                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}