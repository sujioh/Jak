package com.jh.studymate;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {
    private String dbPass="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        final Intent intent = getIntent();
        final String id = intent.getStringExtra("id");

        SQLiteDatabase db = openOrCreateDatabase("board.db",MODE_PRIVATE,null);
        String sql ="select " +
                "subject, wdate, writer, phone, mail, hit , content, password " +
                "from board where _id = '"+id + "'";

        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToNext()){
            EditText deleteSubject = (EditText)findViewById(R.id.deleteSubject);
            deleteSubject.setText(cursor.getString(0));
            dbPass = cursor.getString(7);

        }
        findViewById(R.id.deleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etPass = (EditText)findViewById(R.id.deletePassword);
                String strPass = etPass.getText().toString();
                if(dbPass.equals(strPass)){

                    SQLiteDatabase db = openOrCreateDatabase("board.db",MODE_PRIVATE,null);
                    String sql = "delete from board where _id = '" +id +"' and password = '"+ strPass+"'";

                    db.execSQL(sql);
                    db.close();
                    finish();
                }else {
                    Toast.makeText(DeleteActivity.this,"비밀번호가 틀렸습니다",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
        }


