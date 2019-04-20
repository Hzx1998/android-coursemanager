package com.example.stumanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class selectedCourseAdapter extends ArrayAdapter<SelectedCourse> {
    private int resourceId;
    private Context context;
    private String stuId;
    ArrayList<SelectedCourse> obj;
    /**
     * Constructor
     *
     * @param context            The current context.
     * @param textViewResourceId The id of the TextView within the layout resource to be populated
     * @param objects            The objects to represent in the ListView.
     */
    public selectedCourseAdapter(@NonNull Context context, int textViewResourceId, @NonNull ArrayList<SelectedCourse> objects,String stuid) {
        super(context, textViewResourceId, objects);
        resourceId=textViewResourceId;
        this.context=context;
        this.stuId=stuid;
        this.obj=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Button holder;
         View view;
         if(convertView == null){
             view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
             holder=(Button)view.findViewById(R.id.deletecou);
             view.setTag(holder);
         }else{
             view=convertView;
             holder=(Button)view.getTag();
         }
         final SelectedCourse item = getItem(position);
        TextView scName= (TextView)view.findViewById(R.id.scname);
        TextView scScore=(TextView)view.findViewById(R.id.scscore);
        scName.setText(item.getname());
        if(item.getScores()==null){
            scScore.setText("分数：未发布");
        }else {
            scScore.setText("分数："+item.getScores());
        }
        View.OnClickListener listener=new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                 if(v==holder){
                     SQLite helper=new SQLite(context,"course.db",null,1);
                     SQLiteDatabase db=helper.getWritableDatabase();
                     db.delete("choose","stuid=? and couid=?",new String[]{stuId,item.getid()});
                     Toast.makeText(context, "退选成功，请重新查询", Toast.LENGTH_SHORT).show();

                 }
            }
        } ;
       holder.setOnClickListener(listener);


        return view;
    }
}
