package com.example.stumanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SQLite helper= new SQLite(this,"course.db",null,1);

        Button minebutton = (Button) findViewById(R.id.login);
        final EditText stuId,password;
        stuId = (EditText) findViewById(R.id.stuId) ;
        password = (EditText) findViewById(R.id.password);

        minebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=helper.getWritableDatabase();
                Intent intent = new Intent(MainActivity.this,ChooseActivity.class);
                Intent rootintend=new Intent(MainActivity.this,ManagerActivity.class);
                String stuID = stuId.getText().toString();
                String psw = password.getText().toString();
                final String STU="student";
                final String COU="course";

                Cursor c=db.query("student",null,null,null,null,null,null);
                Cursor c1=db.query("student",null,"stuid=? and stupass=?",new String[]{stuID,psw},null,null,null);
                if(db!=null&&c.getCount()==0){
                    db.execSQL("INSERT INTO "+STU+"(stuid,stupass,stuname,stuage,stusex,stuphone) VALUES('20160101','1234','小明','18','male','123456')");
                    db.execSQL("INSERT INTO "+STU+"(stuid,stupass,stuname,stuage,stusex,stuphone) VALUES('20160102','1234','李华','18','female','178946')");
                    db.execSQL("INSERT INTO "+STU+"(stuid,stupass,stuname,stuage,stusex,stuphone) VALUES('20160103','1234','李林','18','female','178946')");
                    db.execSQL("INSERT INTO "+STU+"(stuid,stupass,stuname,stuage,stusex,stuphone) VALUES('root','root','李林','18','female','178946')");
                    db.execSQL("INSERT INTO "+COU+"(couid,couname,coucredit,counum) VALUES('0101','数据结构','4','40')");
                    db.execSQL("INSERT INTO "+COU+"(couid,couname,coucredit,counum) VALUES('0102','计算机网络','3','30')");
                    db.execSQL("INSERT INTO "+COU+"(couid,couname,coucredit,counum) VALUES('0112','机器学习','4','40')");
                    db.execSQL("INSERT INTO "+COU+"(couid,couname,coucredit,counum) VALUES('0202','中国近代史','3','30')");
                    db.execSQL("INSERT INTO "+COU+"(couid,couname,coucredit,counum) VALUES('0203','大学英语','3','30')");
                    db.execSQL("INSERT INTO "+COU+"(couid,couname,coucredit,counum) VALUES('0301','微积分','3','30')");
                    db.execSQL("INSERT INTO "+COU+"(couid,couname,coucredit,counum) VALUES('0402','大学物理','3','30')");
                    db.execSQL("INSERT INTO "+COU+"(couid,couname,coucredit,counum) VALUES('0401','在影视中学英语','3','30')");
                    db.execSQL("INSERT INTO "+COU+"(couid,couname,coucredit,counum) VALUES('0802','马克思原理','3','30')");
                    db.execSQL("INSERT INTO "+COU+"(couid,couname,coucredit,counum) VALUES('0501','微机原理','3','30')");
                    db.execSQL("INSERT INTO "+COU+"(couid,couname,coucredit,counum) VALUES('0503','计算机组成','3','30')");
                    db.execSQL("INSERT INTO "+COU+"(couid,couname,coucredit,counum) VALUES('0408','思修','3','30')");
                    db.execSQL("INSERT INTO "+COU+"(couid,couname,coucredit,counum) VALUES('0602','计算机导论','3','30')");
                    db.execSQL("INSERT INTO "+COU+"(couid,couname,coucredit,counum) VALUES('0507','无线传感器','3','30')");
                    db.execSQL("INSERT INTO "+COU+"(couid,couname,coucredit,counum) VALUES('0606','大学英语二','3','30')");
                }
                boolean permitlogin = true;

                if(stuID.length() == 0){
                    Toast.makeText(MainActivity.this,"请输入学号",Toast.LENGTH_SHORT).show();
                    permitlogin=false;
                }else if(psw.length()==0) {
                    Toast.makeText(MainActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    permitlogin = false;
                }else if(c1.getCount()==0){
                    Toast.makeText(MainActivity.this,"学号或密码错误",Toast.LENGTH_SHORT).show();
                    permitlogin=false;
                }
                else{
                    permitlogin=true;
                }

                if(permitlogin){
                    if(stuID.equals("root")) {
                        startActivity(rootintend);
                    }else{
                        String name = null;
                        c1.moveToFirst();
                        name = c1.getString(3);
                        intent.putExtra("id", stuID);
                        intent.putExtra("name", name);
                        startActivity(intent);
                    }
                    finish();
                }


            }
        });
    }
}
