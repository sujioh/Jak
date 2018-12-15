package com.jh.studymate;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList <BoardTO> boardRecords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView)findViewById(R.id.listView);
        readDatabase();


        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(ListActivity.this,ViewActivity.class);
                intent.putExtra("id",boardRecords.get(position).get_id());
                startActivityForResult(intent,1);
            }
        });

        findViewById(R.id.listButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ListActivity.this,WriteActivity.class);
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0) {
            if(resultCode == RESULT_OK){}
        }
        if(requestCode == 1) {
            if(resultCode == RESULT_OK){}
        }
        readDatabase();
    }
    //반복구문은 메소드처리한다
    private void readDatabase() {
        ArrayList<String> data1 = new ArrayList<>();
        SQLiteDatabase db = openOrCreateDatabase("board.db",MODE_PRIVATE,null);
        String sql = "";
        sql +="create table if not exists board(\n" +
                "_id integer primary key autoincrement,\n" +
                "subject text not null,\n" +
                "writer text not null,\n" +
                "mail text,\n" +
                "phone text not null,\n" +
                "password text not null,\n" +
                "content text,\n" +
                "hit integer not null,\n" +
                "wdate date not null)";
        db.execSQL(sql);

        boardRecords = new ArrayList<BoardTO>();

        sql ="select _id ,subject, writer, mail, " +
                "phone ,password, content, hit, wdate " +
                "from board";
        Cursor cursor = db.rawQuery(sql,null);

        while (cursor.moveToNext()) {
            BoardTO boardTo = new BoardTO();
            boardTo.set_id(cursor.getString(0));
            boardTo.setSubject(cursor.getString(1));
            boardTo.setWriter(cursor.getString(2));
            boardTo.setMail(cursor.getString(3));
            boardTo.setPhone(cursor.getString(4));
            boardTo.setPassword(cursor.getString(5));
            boardTo.setContent(cursor.getString(6));
            boardTo.setHit(cursor.getString(7));
            boardTo.setWdate(cursor.getString(8));

           /* String _id =cursor.getString(0);
            String subject =cursor.getString(1);
            String writer =cursor.getString(2);
            String mail =cursor.getString(3);
            String phone =cursor.getString(4);
            String password =cursor.getString(5);
            String content =cursor.getString(6);
            String wdate =cursor.getString(7);*/


            String recorde = String.format("번호: %s \n제목: %s \n작성일: %s \n메일: %s"
                    , boardTo.get_id(), boardTo.getSubject(),boardTo.getWdate(),boardTo.getMail());
            data1.add(recorde);
            boardRecords.add(boardTo);
        }
        cursor.close();
        db.close();
        ArrayAdapter<String> adapter =new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,data1);

        listView.   setAdapter(adapter);
    }
}