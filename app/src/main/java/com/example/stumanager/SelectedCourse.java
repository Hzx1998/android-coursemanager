package com.example.stumanager;

import java.util.Random;

public class SelectedCourse {
    String id;
    String name;
    String scores;
    SelectedCourse(String a,String b,String c){
        id=a;
        name=b;
        scores=c;
    }

    public String getname(){ return name; }
    public String getid(){
        return id;
    }
    public String getScores(){ return scores; }
}
