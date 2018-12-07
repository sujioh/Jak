package com.jh.myapplication_5;

import android.content.Intent;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.icu.util.Calendar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class TodoActivity extends AppCompatActivity {

    DbHelper dbHelper;
    ArrayAdapter<String> mAdapter;
    ListView lstTask;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        Toolbar toolbar = findViewById(R.id.toolbar);

        dbHelper = new DbHelper(this);

        lstTask = (ListView)findViewById(R.id.lstTask);

        loadTaskList();

        // 오늘 날짜 설정
        TextView iYear = (TextView)findViewById(R.id.iYear);
        TextView iMonth = (TextView)findViewById(R.id.iMonth);
        TextView iDate = (TextView)findViewById(R.id.iDate);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMMM/dd", Locale.US);
        String strDate = sdf.format(cal.getTime());

        String[] values = strDate.split("/",0);

        iYear.setText(values[0]);
        iMonth.setText(values[1]);
        iDate.setText(values[2]);
    }

    private void loadTaskList() {
        ArrayList<String> taskList = dbHelper.getTaskList();
        if(mAdapter==null) {
            mAdapter = new ArrayAdapter<String>(this, R.layout.row, R.id.task_title, taskList);
            lstTask.setAdapter(mAdapter);
        }
        else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todo_menu,menu);

        //change menu icon color
        Drawable icon = menu.getItem(0).getIcon();
        icon.mutate();
        icon.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
        return super.onCreateOptionsMenu(menu);
    }

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add New Task")
                        .setMessage("What do you want to do next?")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                dbHelper.insertNewTask(task);
                                loadTaskList();
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .create();
                dialog.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    */

    public void deleteTask (View view){
        View parent = (View)view.getParent();
        TextView taskTextView = (TextView) findViewById(R.id.task_title);
        String task = String.valueOf(taskTextView.getText());
        dbHelper.deleteTask(task);
        loadTaskList();
    }

    public void ClickButton(View view) {
        final EditText taskEditText = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add New Task")
                .setMessage("What do you want to do next?")
                .setView(taskEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = String.valueOf(taskEditText.getText());
                        dbHelper.insertNewTask(task);
                        loadTaskList();
                    }
                })
                .setNegativeButton("Cancel",null)
                .create();
        dialog.show();
    }
}
