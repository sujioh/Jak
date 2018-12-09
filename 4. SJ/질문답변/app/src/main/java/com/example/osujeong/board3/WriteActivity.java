package com.example.osujeong.board3;
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
                EditText etPassword =(EditText)findViewById(R.id.whitePasswordET);
                EditText etWriter =(EditText)findViewById(R.id.whiteWriterET);
                EditText etContent =(EditText)findViewById(R.id.whiteContentET);

                String stretSubject = etSubject.getText().toString();
                String stretPessword = etPassword.getText().toString();
                String stretWriter = etWriter.getText().toString();
                String stretContent = etContent.getText().toString();

                //필수 입력 항목 검사 (경고창) 토스트 해야함.

                SQLiteDatabase db = openOrCreateDatabase("board.db",MODE_PRIVATE,null);

                //크리에이트 구문을 문자열로 치거나 스트링 빌더, 스트링버퍼 든 마음대로.if not exist
                String sql ="insert into board(\n" +
                        "subject, writer, password, content,hit,wdate)\n" +
                        " values('" + stretSubject+ "','" + stretWriter+"','"+ stretPessword +"','"+stretContent +"',0,datetime('now','localtime'))";
                db.execSQL(sql);

                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}