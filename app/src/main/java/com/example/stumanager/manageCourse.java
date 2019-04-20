package com.example.stumanager;

public class manageCourse {
    String stuid;
    String stuname;
    String couid;
    String couname;
    manageCourse(String a,String b,String c,String d){
        stuid=a;
        stuname=b;
        couid=c;
        couname=d;
    }
    String getStuid(){
        return stuid;
    }
    String getStuname(){
        return stuname;
    }
    String getCouid(){
        return couid;
    }
    String getCouname(){
        return couname;
    }
}
