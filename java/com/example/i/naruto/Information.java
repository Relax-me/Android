package com.example.i.naruto;

import cn.bmob.v3.BmobObject;

/**
 * Created by 或跃在渊i on 2017/11/8.
 */

public class Information extends BmobObject {
    private String nicheng;
    private Integer dengji;
    private Integer jingyan;
    private Integer zhangjie;
    private Integer jinengdian;
    private Integer jineng;
    public String getNicheng(){ return nicheng; }
    public void setNicheng(String nicheng){
        this.nicheng=nicheng;
    }
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
    public Integer getShengmingzhi() {
        int smz=200+getDengji()*100;
        return smz;
    }
    public Integer getZhandouli() {
        int[] jineng={getJineng()/100000,(getJineng()%100000)/10000,(getJineng()%10000)/1000,(getJineng()%1000)/100,(getJineng()%100)/10,getJineng()%10};
        int zdl=getDengji()*100+getJingyan()+jineng[0]*1000+(jineng[1]+jineng[2]+jineng[3]+jineng[4]+jineng[5])*100;
        return zdl;
    }
}
