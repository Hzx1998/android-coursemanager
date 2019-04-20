package com.example.stumanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class courseAdapter extends ArrayAdapter<Course> {

    private Context context;
    private int resourceId;
    private String stuname;
    private String stuId;
    private ArrayList<Course> obj;
    public courseAdapter(@NonNull Context context,int textViewResourceId, @NonNull ArrayList<Course> objects,String id,String name) {
        super(context, textViewResourceId, objects);
        this.context=context;
        this.obj=objects;
        this.stuname=name;
        this.stuId=id;
        resourceId=textViewResourceId;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public Course getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Button holder;
        View view;
        if(convertView == null){
          view  = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
          holder=(Button)view.findViewById(R.id.choose);
          view.setTag(holder);
        }else{
            view=convertView;
            holder=(Button)view.getTag();
        }
        Course item=getItem(position);
        int i=(int)getItemId(position);
        TextView credit=(TextView)view.findViewById(R.id.credit);
        final TextView name=(TextView)view.findViewById(R.id.couname);
        TextView id=(TextView)view.findViewById(R.id.couId);
        name.setText(item.getname());
        id.setText("课程编号" +item.getid());
        credit.setText(item.getcredit()+"学分");
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==holder){
                    SQLite helper=new SQLite(context,"course.db",null,1);
                    SQLiteDatabase db=helper.getWritableDatabase();
                    int i=(int)getItemId(position);
                    String couname=obj.get(i).name;
                    String couid=obj.get(i).id;
                    Cursor c=db.query("choose",null,"stuid=? and couid=?",new String[]{stuId,couid},null,null,null);
                    if(c.getCount()==0){
                        try{
                            ContentValues cv=new ContentValues();
                            cv.put("stuid",stuId);
                            cv.put("stuname",stuname);
                            cv.put("couid",couid);
                            cv.put("couname",couname);
                            db.insert("choose",null,cv);
                            Toast.makeText(context, "选课成功", Toast.LENGTH_SHORT).show();
                            cv.clear();
                            }catch (SQLException e){ }

                     }else{
                           Toast.makeText(context, "你已选择该课程，请勿重复选课", Toast.LENGTH_SHORT).show();
                           }

                 }
             }

        };
        holder.setOnClickListener(listener);
        return view;
    }
}
