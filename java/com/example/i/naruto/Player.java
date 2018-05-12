package com.example.i.naruto;

import android.app.Application;

public class Player extends Application{
    private String nicheng;
    private Integer dengji;
    private Integer jingyan;
    private Integer zhangjie;
    private Integer jinengdian;
    private Integer jineng;
    private String objectId;
    public String getObjectId() {
        return objectId;
    }
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
    public Integer getjineng1(){
        return getJineng()%10;
    }
    public Integer getjineng2(){
        return (getJineng()%100)/10;
    }
    public Integer getjineng3(){
        return (getJineng()%1000)/100;
    }
    public Integer getjineng4(){
        return (getJineng()%10000)/1000;
    }
    public Integer getjineng5(){
        return (getJineng()%100000)/10000;
    }
    public Integer getjineng6(){
        return getJineng()/100000;
    }
    public Integer getGongjili() {
        return 30+getDengji()*5;
    }
    public Integer getShengmingzhi() {
        return 200+getDengji()*100;
    }
    public Integer getZhandouli() {
        int[] jineng={getJineng()/100000,(getJineng()%100000)/10000,(getJineng()%10000)/1000,(getJineng()%1000)/100,(getJineng()%100)/10,getJineng()%10};
        return getDengji()*100+getJingyan()+jineng[0]*1000+(jineng[1]+jineng[2]+jineng[3]+jineng[4]+jineng[5])*100;
    }
    public String getNicheng(){ return nicheng; }
    public void setNicheng(String nicheng){ this.nicheng=nicheng; }
    public Integer getDengji(){
        return dengji;
    }
    public void setDengji(Integer dengji){
        this.dengji=dengji;
    }
    public Integer getJingyan(){
        return jingyan;
    }
    public void setJingyan(Integer jingyan){
        this.jingyan=jingyan;
    }
    public Integer getZhangjie(){
        return zhangjie;
    }
    public void setZhangjie(Integer zhangjie){
        this.zhangjie=zhangjie;
    }
    public Integer getJinengdian(){
        return jinengdian;
    }
    public void setJinengdian(Integer jinengdian){
        this.jinengdian=jinengdian;
    }
    public Integer getJineng(){
        return jineng;
    }
    public void setJineng(int jineng){  this.jineng=jineng; }
    @Override
    public void onCreate() {
        super.onCreate();
        nicheng="Naruto";
        dengji=1;
        jingyan=0;
        zhangjie=1;
        jinengdian=0;
        jineng=0;
    }
}
