package com.example.stumanager;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        Intent getValue = getIntent();
        final String id = getValue.getStringExtra("id");
        final String name =getValue.getStringExtra("name");
        TextView stuInfo = findViewById(R.id.stuinfo);
        stuInfo.setText("姓名:"+name+"      学号:"+id);
        Button detailbtn=findViewById(R.id.detail);
        SQLite helper = new SQLite(this,"course.db",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor stuc=db.query("student",null,"stuid=?",new String[]{id},null,null,null);
        stuc.moveToFirst();
        final String stusex=stuc.getString(5);
        final String stuage=stuc.getString(4);
        final String stuphone=stuc.getString(6);

        detailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 //Cursor stuc=db.query("student",null,"stuid=?",new String[]{id},null,null,null);
                //stuc.moveToFirst();
                 AlertDialog.Builder diaLog = new AlertDialog.Builder(ChooseActivity.this);
                 diaLog.setTitle("详细信息");
                 diaLog.setMessage("姓名："+name+"\n学号："+id+"\n性别："+stusex+"\n年龄："+stuage+"\n电话："+stuphone);
                 diaLog.show();

            }
        });

        Cursor c = db.query("course",null,null,null,null,null,null);
        c.moveToFirst();
        final ArrayList<Course> Acou = new ArrayList<Course>();

        if(c.moveToFirst()){
            do{
                String couid=c.getString(1);
                String couname=c.getString(2);
                String coucredit=c.getString(3);
                Acou.add(new Course(couid,couname,coucredit));
            }while(c.moveToNext());
        }
        c.close();
        db.close();
        courseAdapter adapter=new courseAdapter(ChooseActivity.this,R.layout.cou_item,Acou,id,name);
        ListView listview = findViewById(R.id.Lv);
        listview.setAdapter(adapter);

        Button minebutton = findViewById(R.id.mine);
        minebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ChooseActivity.this,MineActivity.class);
                intent1.putExtra("id",id);
                startActivity(intent1);
            }
        });
    }
}
