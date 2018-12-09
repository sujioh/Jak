package com.example.osujeong.board3;

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
    private String dtPassword = "";
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
        final EditText etContent =(EditText)findViewById(R.id.modifyContent);

        SQLiteDatabase db = openOrCreateDatabase("board.db",MODE_PRIVATE,null);
        String sql ="select " +
                " _id, subject, writer, password, content " +
                "from board where _id = '"+id + "'";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToNext()){
            //기존 값을 가지고와서 다시 넣어줘요

            etSubject.setText(cursor.getString(1));
            etWriter.setText(cursor.getString(2));
            etPessword.setText(cursor.getString(3));
            etContent.setText(cursor.getString(4));

            dtSubject = cursor.getString(1);
            dtWriter = cursor.getString(2);
            dtPassword = cursor.getString(3);
            dtContent = cursor.getString(4);
            System.out.println(dtContent);
        }
        findViewById(R.id.modifyButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stretSubject = etSubject.getText().toString();
                String stretPessword = etWriter.getText().toString();
                String stretWriter = etPessword.getText().toString();
                String stretContent = etContent.getText().toString();
                SQLiteDatabase db = openOrCreateDatabase("board.db",MODE_PRIVATE,null);

                String sql = "update board set \n" +
                        "subject= '"+ stretSubject+"' ,\n" +
                        "writer= '"+ stretPessword+"' ,\n" +
                        "password= '"+ stretWriter+"' ,\n"  +
                        "content= '"+stretContent +"' \n" +
                        "where _id = " + id ;

                db.execSQL(sql);

                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        });



    }
}


