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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class managerAdapter extends ArrayAdapter<manageCourse> {
    private int resourceId;
    /**
     * Constructor
     *
     * @param context            The current context.
     * @param textViewResourceId The id of the TextView within the layout resource to be populated
     * @param objects            The objects to represent in the ListView.
     */
    public managerAdapter(@NonNull Context context, int textViewResourceId, @NonNull ArrayList<manageCourse> objects) {
        super(context, textViewResourceId, objects);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        }else{
            view=convertView;
        }
        final manageCourse item = getItem(position);
        TextView t1=(TextView)view.findViewById(R.id.cou_id);
        TextView t2=(TextView)view.findViewById(R.id.cou_name);
        TextView t3=(TextView)view.findViewById(R.id.stu_id);
        TextView t4=(TextView)view.findViewById(R.id.stu_name);
        t1.setText("编号："+item.getCouid());
        t2.setText(item.getCouname());
        t3.setText("学号："+item.getStuid());
        t4.setText(item.getStuname());
        return view;
    }
}
