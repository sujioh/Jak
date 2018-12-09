package com.example.osujeong.board3;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        final Intent intent = getIntent();
        final String id = intent.getStringExtra("id");

        SQLiteDatabase db = openOrCreateDatabase("board.db",MODE_PRIVATE,null);
        String sql ="select " +
                "subject, wdate, writer, hit , content " +
                "from board where _id = "+id ;
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToNext()){
            TextView viewSubjectET =(TextView)findViewById(R.id.viewSubjectET);
            TextView viewWdateET =(TextView)findViewById(R.id.viewWdateET);
            TextView viewWriterET =(TextView)findViewById(R.id.viewWriterET);
            TextView viewHitET =(TextView)findViewById(R.id.viewHitET);
            TextView viewContentET =(TextView)findViewById(R.id.viewContentET);

            viewSubjectET.setText(cursor.getString(0));
            viewWdateET.setText(cursor.getString(1));
            viewWriterET.setText(cursor.getString(2));
            viewHitET.setText(cursor.getString(3));
            viewContentET.setText(cursor.getString(4));

            findViewById(R.id.viewReMoveButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //수정버튼
                    Intent intent=new Intent(ViewActivity.this,ModifyActivity.class);
                    intent.putExtra("id",id);
                    startActivityForResult(intent,3);//3은 코드 값이란다.
                }
            });
            findViewById(R.id.viewDeleteButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //삭제버튼
                    Intent intent=new Intent(ViewActivity.this,DeleteActivity.class);
                    intent.putExtra("id",id);
                    startActivityForResult(intent,4);
                    finish();
                }
            });
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 3){
            if(resultCode == RESULT_OK){
                //모디파이 엑티비티
            }
        }
        if(requestCode == 4){
            if(resultCode == RESULT_OK){
                //딜리트 엑티비티
            }
        }
    } private void readDatabase() {

    }

}

