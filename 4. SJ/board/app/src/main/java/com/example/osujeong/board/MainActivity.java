package com.example.osujeong.board;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    class Board{
        String title;
        String content;

        Board() {}

        Board(String title, String content){

            this.title=title;
            this.content=content;
        }
        public String getTitle() {return title;}
        public String getContent(){return content;}
    }


    public void clickbutton(View v){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        Board board = new Board(edittext2.getText().toString());
        database.child("message").push().setValue(board);
    }
}
