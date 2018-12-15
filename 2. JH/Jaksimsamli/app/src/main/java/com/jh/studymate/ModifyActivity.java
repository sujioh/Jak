package com.jh.studymate;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ModifyActivity extends AppCompatActivity {
    private String dtSubject = "";
    private String dtWriter = "";
    private String dtPessword = "";
    private String dtMail = "";
    private String dtPhone = "";
    private String dtContent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        final Intent intent = getIntent();
        final String id = intent.getStringExtra("id");

        final EditText etSubject =(EditText)findViewById(R.id.modifySubject);
        final EditText etWriter =(EditText)findViewById(R.id.modifyWriter);
        final EditText etPessword =(EditText)findViewById(R.id.modifyPassword);
        final EditText etMail =(EditText)findViewById(R.id.modifyMail);
        final EditText etPhone =(EditText)findViewById(R.id.modifyPhone);
        final EditText etContent =(EditText)findViewById(R.id.modifyContent);

        SQLiteDatabase db = openOrCreateDatabase("board.db",MODE_PRIVATE,null);
        String sql ="select " +
                " _id, subject, writer, password, mail, phone, content " +
                "from board where _id = '"+id + "'";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToNext()){


            etSubject.setText(cursor.getString(1));
            etWriter.setText(cursor.getString(2));
            etPessword.setText(cursor.getString(3));
            etMail.setText(cursor.getString(4));
            etPhone.setText(cursor.getString(5));
            etContent.setText(cursor.getString(6));

            dtSubject = cursor.getString(1);
            dtWriter = cursor.getString(2);
            dtPessword = cursor.getString(3);
            dtMail = cursor.getString(4);
            dtPhone = cursor.getString(5);
            dtContent = cursor.getString(6);
            System.out.println(dtContent);
        }
        findViewById(R.id.modifyButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stretSubject = etSubject.getText().toString();
                String stretPessword = etWriter.getText().toString();
                String stretWriter = etPessword.getText().toString();
                String stretContent = etMail.getText().toString();
                String stretMail = etMail.getText().toString();
                String stretPhon = etPhone.getText().toString();
                SQLiteDatabase db = openOrCreateDatabase("board.db",MODE_PRIVATE,null);

                String sql = "update board set \n" +
                        "subject= '"+ stretSubject+"' ,\n" +
                        "writer= '"+ stretPessword+"' ,\n" +
                        "password= '"+ stretWriter+"' ,\n"  +
                        "mail= '"+ stretContent+"' ,\n" +
                        "phone= '"+ stretMail+"' ,\n"  +
                        "content= '"+stretPhon +"' \n" +
                        "where _id = " + id ;

                db.execSQL(sql);

                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        });



    }
}


