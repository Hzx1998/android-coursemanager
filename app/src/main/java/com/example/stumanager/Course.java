package com.example.stumanager;

class Course{
    String id;
    String name;
    String credit;
    Course(String a,String b,String c){
        id=a;
        name=b;
        credit=c;
    }

    public String getname(){

        return name;
    }
    public String getid(){
        return id;
    }
    public String getcredit(){
        return credit;
    }



}