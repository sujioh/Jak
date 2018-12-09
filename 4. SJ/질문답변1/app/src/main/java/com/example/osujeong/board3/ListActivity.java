package com.example.osujeong.board3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    DBHelper myDb;

    Button btnAddData;
    ListView listView;

    private ArrayList <BoardTO> boardRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        myDb = new DBHelper(this);

        btnAddData = (Button)findViewById(R.id.listButton);
        listView = (ListView)findViewById(R.id.ListView);

        readDatabase();//맨처음은 테이블이 없기때문에 애러날 수 있다. 그러니까 크리에이트 부분을
        //셀렉트 전의 위치로 옮긴다.

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //포지션을 통해 어레이리스트에 접근
                //System.out.println(boardRecords.get(position).get_id());
                Intent intent=new Intent(ListActivity.this,ViewActivity.class);
                intent.putExtra("id",boardRecords.get(position).get_id());
                startActivityForResult(intent,1);//1번 이라는 키값을 가짐
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
        if(requestCode == 0) { //write 엑티비티- 인텐트가 0인 거를 가져오는거임
            if(resultCode == RESULT_OK){}
        }
        if(requestCode == 1) {  //View 엑티비티- 인텐트가 1인 거를 가져오는거임
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
        db.execSQL(sql); //테이블 생성

        boardRecords = new ArrayList<BoardTO>();//아이디 담는 어레이리스트

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


            String recorde = String.format("글번호:%s /제목:%s /글쓴이:%s /메일 :%s"
                    , boardTo.get_id(), boardTo.getSubject(),boardTo.getWdate(),boardTo.getMail());
            data1.add(recorde);
            boardRecords.add(boardTo);//조심해야 할게. 반드시 뉴 해야 합니다.
        }
        cursor.close();
        db.close();
        ArrayAdapter<String> adapter =new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,data1);

        listView.setAdapter(adapter);
    }
}