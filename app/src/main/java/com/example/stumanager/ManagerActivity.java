package com.example.stumanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        SQLite helper=new SQLite(this,"course.db",null,1);
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor c=db.query("choose",null,null,null,null,null,null);
        final ArrayList<manageCourse> mCou = new ArrayList<manageCourse>();
        c.moveToFirst();
        if(c.moveToFirst()) {
            do {
                String a1 = c.getString(1);
                String a2 = c.getString(2);
                String a3 = c.getString(3);
                String a4 = c.getString(4);
                mCou.add( new manageCourse(a1, a2, a3, a4));
                //Toast.makeText(ManagerActivity.this,a1,Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        managerAdapter adapter=new managerAdapter(ManagerActivity.this,R.layout.root_item,mCou);
        ListView listview=findViewById(R.id.lv);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                           manageCourse a=mCou.get(i);
                Intent intent=new Intent(ManagerActivity.this,displayActivity.class);
                intent.putExtra("stuid",a.getStuid());
                intent.putExtra("stuname",a.getStuname());
                intent.putExtra("couid",a.getCouid());
                intent.putExtra("couname",a.getCouname());
                startActivity(intent);
            }
        });
    }
}
