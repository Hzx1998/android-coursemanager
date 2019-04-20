package com.example.stumanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        Intent getintent=getIntent();
        final String id = getintent.getStringExtra("id");
        SQLite helper = new SQLite(this,"course.db",null,1);
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor c=db.query("choose",null,"stuid=?",new String[]{id},null,null,null);
        ArrayList<SelectedCourse> SAcou = new ArrayList<SelectedCourse>();
        c.moveToFirst();
        if(c.moveToFirst()) {
            do {
                String scname = c.getString(4);
                String scid = c.getString(3);
                String scscore = c.getString(5);

                SAcou.add( new SelectedCourse(scid, scname,scscore));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        selectedCourseAdapter adapter=new selectedCourseAdapter(MineActivity.this,R.layout.scou_item,SAcou,id);
        ListView LV=findViewById(R.id.sclv);
        LV.setAdapter(adapter);
    }
}
