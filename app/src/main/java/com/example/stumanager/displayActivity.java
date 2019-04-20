package com.example.stumanager;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class displayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Intent getvalue=getIntent();
        final String stuid=getvalue.getStringExtra("stuid");
        String stuname=getvalue.getStringExtra("stuname");
        final String couid=getvalue.getStringExtra("couid");
        String couname=getvalue.getStringExtra("couname");
        TextView t1=(TextView)findViewById(R.id.textView6);
        TextView t2=(TextView)findViewById(R.id.textView7);
        TextView t3=(TextView)findViewById(R.id.textView5);
        TextView t4=(TextView)findViewById(R.id.textView8);
        t1.setText("编号："+couid);
        t2.setText("课程："+couname);
        t3.setText("学号："+stuid);
        t4.setText("姓名："+stuname);
        final EditText e=findViewById(R.id.score);
        Button b=findViewById(R.id.givescore);

        final SQLite helper=new SQLite(displayActivity.this,"course.db",null,1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String score=e.getText().toString();
                SQLiteDatabase db=helper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("couscore",score);
                        db.update("choose",values,"stuid=? and couid=?",new String[]{stuid,couid});
                Toast.makeText(displayActivity.this,"打分成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

