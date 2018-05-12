package com.example.i.naruto;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class UserActivity extends AppCompatActivity {
    private Intent musicintent = new Intent("com.angel.Android.MUSICuser");
    private Player player;
    private int zhangjienow;
    private int jinengupnow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        player=(Player)getApplication();
        init();
        setListener();
        startService(musicintent);
    }
    protected void initzhujiemian() {
        initInformation();
        setContentView(R.layout.activity_user);
        TextView nicheng = (TextView) findViewById(R.id.nicheng);
        nicheng.setText(""+player.getNicheng());
        TextView dengji = (TextView) findViewById(R.id.dengji);
        dengji.setText(""+player.getDengji());
        TextView jingyan = (TextView) findViewById(R.id.jingyan);
        jingyan.setText(""+player.getJingyan()+"/"+(player.getDengji()*100+200));
        TextView jinengdian = (TextView) findViewById(R.id.jinengdian);
        jinengdian.setText(""+player.getJinengdian());
        setListener();
    }
    protected void init(){
        BmobQuery<Information> query = new BmobQuery<Information>();
        query.addWhereEqualTo("nicheng", LoginActivity.nicheng);
        //执行查询方法
        query.findObjects(new FindListener<Information>() {
            @Override
            public void done(List<Information> var1, BmobException var2) {

                if (var1 != null) {
                    for (Information information:var1) {
                        TextView nicheng = (TextView) findViewById(R.id.nicheng);
                        nicheng.setText(""+information.getNicheng());
                        TextView dengji = (TextView) findViewById(R.id.dengji);
                        dengji.setText(""+information.getDengji());
                        TextView jingyan = (TextView) findViewById(R.id.jingyan);
                        jingyan.setText(""+information.getJingyan()+"/"+(information.getDengji()*100+200));
                        TextView jinengdian = (TextView) findViewById(R.id.jinengdian);
                        jinengdian.setText(""+information.getJinengdian());
                        break;
                    }
                }else{ }
            }
        });
    }
    protected void initfight(){
        stopService(musicintent);
        setContentView(R.layout.loading);
        Intent intentfight = new Intent(UserActivity.this, FightActivity.class);
        startActivity(intentfight);
        finish();
    }
    protected void initjineng(){
        setContentView(R.layout.jineng);
        jinengupnow=0;
        jinengshow();
        setListenerjineng();
    }
    protected void initxinxi(){
        setContentView(R.layout.xinxi);
        TextView nicheng = (TextView) findViewById(R.id.xinxinicheng);
        nicheng.setText(""+player.getNicheng());
        TextView dengji = (TextView) findViewById(R.id.xinxidengji);
        dengji.setText(""+player.getDengji());
        TextView jingyan = (TextView) findViewById(R.id.xinxijingyan);
        jingyan.setText(""+player.getJingyan()+"/"+(player.getDengji()*100+200));
        TextView zhanli = (TextView) findViewById(R.id.xinxizhandouli);
        zhanli.setText(""+player.getZhandouli());
        TextView shengming = (TextView) findViewById(R.id.xinxishengming);
        shengming.setText(""+player.getShengmingzhi());
        TextView gongji = (TextView) findViewById(R.id.xinxigongjili);
        gongji.setText(""+player.getGongjili());
        setListenerxinxi();
    }
    protected void initboss(){
        setContentView(R.layout.boss);
        setListenerboss();
    }
    protected void initpaihang(){
        setContentView(R.layout.paihang);
        BmobQuery<Information> query = new BmobQuery<Information>();
        //执行查询方法
        query.order("-dengji,-jingyan");
        query.findObjects(new FindListener<Information>() {
            @Override
            public void done(List<Information> var1, BmobException var2) {

                if (var1 != null) {
                    int num=1;
                    for (Information information:var1) {
                        switch (num) {
                            case 1:{
                                TextView nicheng = (TextView) findViewById(R.id.nicheng1);
                                nicheng.setText("" + information.getNicheng());
                                TextView dengji = (TextView) findViewById(R.id.dengji1);
                                dengji.setText("" + information.getDengji());
                                TextView zhanli=(TextView)findViewById(R.id.zhanli1);
                                zhanli.setText(""+information.getZhandouli());
                                break;
                            }
                            case 2:{
                                TextView nicheng = (TextView) findViewById(R.id.nicheng2);
                                nicheng.setText("" + information.getNicheng());
                                TextView dengji = (TextView) findViewById(R.id.dengji2);
                                dengji.setText("" + information.getDengji());
                                TextView zhanli=(TextView)findViewById(R.id.zhanli2);
                                zhanli.setText(""+information.getZhandouli());
                                break;
                            }
                            case 3:{
                                TextView nicheng = (TextView) findViewById(R.id.nicheng3);
                                nicheng.setText("" + information.getNicheng());
                                TextView dengji = (TextView) findViewById(R.id.dengji3);
                                dengji.setText("" + information.getDengji());
                                TextView zhanli=(TextView)findViewById(R.id.zhanli3);
                                zhanli.setText(""+information.getZhandouli());
                                break;
                            }
                            case 4:{
                                TextView nicheng = (TextView) findViewById(R.id.nicheng4);
                                nicheng.setText("" + information.getNicheng());
                                TextView dengji = (TextView) findViewById(R.id.dengji4);
                                dengji.setText("" + information.getDengji());
                                TextView zhanli=(TextView)findViewById(R.id.zhanli4);
                                zhanli.setText(""+information.getZhandouli());
                                break;
                            }
                            default:return;

                        }
                        num++;
                    }
                }else{ }
            }
        });
        setListenerpaihang();
    }
    protected void initgushi(){
        zhangjienow=player.getZhangjie()+1;
        setContentView(R.layout.gushi);
        zhangjieshow();
        setListenergushi();
    }
    protected void initInformation(){
        BmobQuery<Information> query = new BmobQuery<Information>();
        query.addWhereEqualTo("nicheng", LoginActivity.nicheng);
        //执行查询方法
        query.findObjects(new FindListener<Information>() {
            @Override
            public void done(List<Information> var1, BmobException var2) {

                if (var1 != null) {
                    for (Information information:var1) {
                        player.setDengji(information.getDengji());
                        player.setJingyan(information.getJingyan());
                        player.setZhangjie(information.getZhangjie());
                        player.setJinengdian(information.getJinengdian());
                        player.setJineng(information.getJineng());
                        player.setObjectId(information.getObjectId());
                        break;
                    }
                }else{ }
            }
        });
    }
    protected void setListener(){
        findViewById(R.id.xinxi).setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            initxinxi();
                                                        }
                                                    }
        );
        findViewById(R.id.paihang).setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View v) {
                                                              initpaihang();
                                                          }
                                                      }
        );
        findViewById(R.id.xiuxing).setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View v) {
                                                              initfight();
                                                          }
                                                      }
        );
        findViewById(R.id.boss).setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View v) {
                                                           initboss();
                                                       }
                                                   }
        );
        findViewById(R.id.gushi).setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            initgushi();
                                                        }
                                                    }
        );
        findViewById(R.id.jineng).setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View v) {
                                                             initjineng();
                                                         }
                                                     }
        );
    }
    protected void setListenerxinxi(){
        findViewById(R.id.xinxiexit).setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                initzhujiemian();
                                                            }
                                                        }
        );
    }
    protected void setListenerboss(){
        findViewById(R.id.bossexit1).setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                initzhujiemian();
                                                            }
                                                        }
        );
        findViewById(R.id.bossexit2).setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                initzhujiemian();
                                                            }
                                                        }
        );
        findViewById(R.id.boss1).setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            initfight();
                                                        }
                                                    }
        );
        findViewById(R.id.boss2).setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            initfight();
                                                        }
                                                    }
        );
        findViewById(R.id.boss3).setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            initfight();
                                                        }
                                                    }
        );
        findViewById(R.id.boss4).setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            initfight();
                                                        }
                                                    }
        );
        findViewById(R.id.boss5).setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            initfight();
                                                        }
                                                    }
        );
    }
    protected void setListenergushi(){
        findViewById(R.id.gushiexit1).setOnClickListener(new View.OnClickListener() {
                                                             @Override
                                                             public void onClick(View v) {
                                                                 initzhujiemian();
                                                             }
                                                         }
        );
        findViewById(R.id.gushiexit2).setOnClickListener(new View.OnClickListener() {
                                                             @Override
                                                             public void onClick(View v) {
                                                                 initzhujiemian();
                                                             }
                                                         }
        );
        findViewById(R.id.kaishi).setOnClickListener(new View.OnClickListener() {
                                                             @Override
                                                             public void onClick(View v) {
                                                                 initfight();
                                                             }
                                                         }
        );
        TextView zhangjie1=(TextView)findViewById(R.id.zhangjie1);
        TextView zhangjie3=(TextView)findViewById(R.id.zhangjie3);
        zhangjie1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(zhangjienow!=1) {
                    zhangjienow = zhangjienow - 1;
                    zhangjieshow();
                }
            }
        });
        zhangjie3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(zhangjienow<player.getZhangjie()+1){
                    zhangjienow = zhangjienow + 1;
                    zhangjieshow();
                }
            }
        });
    }
    protected void setListenerjineng(){
        findViewById(R.id.jinengexit1).setOnClickListener(new View.OnClickListener() {
                                                              @Override
                                                              public void onClick(View v) {
                                                                  initzhujiemian();
                                                              }
                                                          }
        );
        findViewById(R.id.jinengexit2).setOnClickListener(new View.OnClickListener() {
                                                              @Override
                                                              public void onClick(View v) {
                                                                  initzhujiemian();
                                                              }
                                                          }
        );
        findViewById(R.id.jinengup).setOnClickListener(new View.OnClickListener() {
                                                              @Override
                                                              public void onClick(View v) {
                                                                  jinengup();
                                                                  baocun();
                                                                  jinengshow();
                                                              }
                                                          }
        );
        findViewById(R.id.jineng1).setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View v) {
                                                              jinengupnow=1;
                                                              jinengshow();
                                                          }
                                                      }
        );
        findViewById(R.id.jineng2).setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View v) {
                                                              jinengupnow=2;
                                                              jinengshow();
                                                          }
                                                      }
        );
        findViewById(R.id.jineng3).setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View v) {
                                                              jinengupnow=3;
                                                              jinengshow();
                                                          }
                                                      }
        );
        findViewById(R.id.jineng4).setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View v) {
                                                              jinengupnow=4;
                                                              jinengshow();
                                                          }
                                                      }
        );
        findViewById(R.id.jineng5).setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View v) {
                                                              jinengupnow=5;
                                                              jinengshow();
                                                          }
                                                      }
        );
        findViewById(R.id.jineng6).setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View v) {
                                                              jinengupnow=6;
                                                              jinengshow();
                                                          }
                                                      }
        );
    }
    protected void setListenerpaihang(){
        findViewById(R.id.paihangexit).setOnClickListener(new View.OnClickListener() {
                                                              @Override
                                                              public void onClick(View v) {
                                                                  initzhujiemian();
                                                              }
                                                          }
        );
    }
    protected void zhangjieshow(){
        TextView zhangjie1=(TextView)findViewById(R.id.zhangjie1);
        TextView zhangjie2=(TextView)findViewById(R.id.zhangjie2);
        TextView zhangjie3=(TextView)findViewById(R.id.zhangjie3);
        if (zhangjienow==(player.getZhangjie()+1))
        {
            zhangjie3.setTextColor(0xff000000);
        }
        else {
            zhangjie3.setTextColor(0xfffd5901);
        }
        switch (zhangjienow)
        {
            case 1:{
                zhangjie1.setText("");
                zhangjie2.setText("第一章");
                zhangjie3.setText(" 第二章");
                break;
            }
            case 2:{
                zhangjie1.setText(" 第一章");
                zhangjie2.setText("第二章");
                zhangjie3.setText(" 第三章");
                break;
            }
            case 3:{
                zhangjie1.setText(" 第二章");
                zhangjie2.setText("第三章");
                zhangjie3.setText(" 第四章");
                break;
            }
            case 4:{
                zhangjie1.setText(" 第三章");
                zhangjie2.setText("第四章");
                zhangjie3.setText(" 第五章");
                break;
            }
            case 5:{
                zhangjie1.setText(" 第四章");
                zhangjie2.setText("第五章");
                zhangjie3.setText(" 第六章");
                break;
            }
            case 6:{
                zhangjie1.setText(" 第五章");
                zhangjie2.setText("第六章");
                zhangjie3.setText(" 第七章");
                break;
            }
            case 7:{
                zhangjie1.setText(" 第六章");
                zhangjie2.setText("第七章");
                zhangjie3.setText(" 第八章");
                break;
            }
            case 8:{
                zhangjie1.setText(" 第七章");
                zhangjie2.setText("第八章");
                zhangjie3.setText(" 第九章");
                break;
            }
            case 9:{
                zhangjie1.setText(" 第八章");
                zhangjie2.setText("第九章");
                zhangjie3.setText(" 第十章");
                break;
            }
            case 10:{
                zhangjie1.setText(" 第九章");
                zhangjie2.setText("第十章");
                zhangjie3.setText("");
                break;
            }
            default:
                zhangjie1.setText(" 第九章");
                zhangjie2.setText("第十章");
                zhangjie3.setText("");
        }
    }
    protected void jinengshow(){
        TextView jinengdianshengyu=(TextView)findViewById(R.id.jinengdianshengyu);
        jinengdianshengyu.setText(""+player.getJinengdian());
        if(player.getjineng1()!=0)findViewById(R.id.jineng1).setBackgroundResource(R.drawable.jineng1);
        if(player.getjineng2()!=0)findViewById(R.id.jineng2).setBackgroundResource(R.drawable.jineng2);
        if(player.getjineng3()!=0)findViewById(R.id.jineng3).setBackgroundResource(R.drawable.jineng3);
        if(player.getjineng4()!=0)findViewById(R.id.jineng4).setBackgroundResource(R.drawable.jineng4);
        if(player.getjineng4()!=0)findViewById(R.id.jineng5).setBackgroundResource(R.drawable.jineng5);
        if(player.getjineng5()!=0)findViewById(R.id.jineng6).setBackgroundResource(R.drawable.jineng6);
        Button jinengnow=(Button)findViewById(R.id.jinengnow);
        Button jinengup=(Button)findViewById(R.id.jinengup);
        TextView jinengnowname = (TextView) findViewById(R.id.jinengnowname);
        TextView jinengnowshanghai = (TextView) findViewById(R.id.jinengnowshanghai);
        TextView jinengnextshanghai = (TextView) findViewById(R.id.jinengnextshanghai);
        switch (jinengupnow){
            case 1:{
                if(player.getjineng1()==0) {
                    jinengnow.setBackgroundResource(R.drawable.jineng01);
                    jinengup.setText("暂未习得");
                }
                else{
                    jinengnow.setBackgroundResource(R.drawable.jineng1);
                    jinengup.setText("升级");
                }
                jinengnowname.setText("水遁-大瀑布之术("+player.getjineng1()+")级");
                jinengnowshanghai.setText(""+player.getjineng1()*100);
                jinengnextshanghai.setText(""+(player.getjineng1()+1)*100);
                if(player.getjineng1()==3)jinengnextshanghai.setText("已满级");
                break;
            }
            case 2:{
                if(player.getjineng2()==0) {
                    jinengnow.setBackgroundResource(R.drawable.jineng02);
                    jinengup.setText("暂未习得");
                }
                else{
                    jinengnow.setBackgroundResource(R.drawable.jineng2);
                    jinengup.setText("升级");
                }
                jinengnowname.setText("火遁-豪火球之术("+player.getjineng2()+")级");
                jinengnowshanghai.setText(""+player.getjineng2()*100);
                jinengnextshanghai.setText(""+(player.getjineng2()+1)*100);
                if(player.getjineng2()==3)jinengnextshanghai.setText("已满级");
                break;
            }
            case 3:{
                if(player.getjineng3()==0) {
                    jinengnow.setBackgroundResource(R.drawable.jineng03);
                    jinengup.setText("暂未习得");
                }
                else{
                    jinengnow.setBackgroundResource(R.drawable.jineng3);
                    jinengup.setText("升级");
                }
                jinengnowname.setText("土遁-土龙弹("+player.getjineng3()+")级");
                jinengnowshanghai.setText(""+player.getjineng3()*100);
                jinengnextshanghai.setText(""+(player.getjineng3()+1)*100);
                if(player.getjineng3()==3)jinengnextshanghai.setText("已满级");
                break;
            }
            case 4:{
                if(player.getjineng4()==0) {
                    jinengnow.setBackgroundResource(R.drawable.jineng04);
                    jinengup.setText("暂未习得");
                }
                else{
                    jinengnow.setBackgroundResource(R.drawable.jineng4);
                    jinengup.setText("升级");
                }
                jinengnowname.setText("金遁-破晶降龙之术("+player.getjineng4()+")级");
                jinengnowshanghai.setText(""+player.getjineng4()*100);
                jinengnextshanghai.setText(""+(player.getjineng4()+1)*100);
                if(player.getjineng4()==3)jinengnextshanghai.setText("已满级");
                break;
            }
            case 5:{
                if(player.getjineng5()==0) {
                    jinengnow.setBackgroundResource(R.drawable.jineng05);
                    jinengup.setText("暂未习得");
                }
                else{
                    jinengnow.setBackgroundResource(R.drawable.jineng5);
                    jinengup.setText("升级");
                }
                jinengnowname.setText("木遁-扦插之术("+player.getjineng5()+")级");
                jinengnowshanghai.setText(""+player.getjineng5()*100);
                jinengnextshanghai.setText(""+(player.getjineng5()+1)*100);
                if(player.getjineng5()==3)jinengnextshanghai.setText("已满级");
                break;
            }
            case 6:{
                if(player.getjineng6()==0) {
                    jinengnow.setBackgroundResource(R.drawable.jineng06);
                    jinengup.setText("暂未习得");
                }
                else{
                    jinengnow.setBackgroundResource(R.drawable.jineng6);
                    jinengup.setText("升级");
                }
                jinengnowname.setText("螺旋丸("+player.getjineng6()+")级");
                if(player.getjineng6()==3){
                    jinengnowname.setText("超大玉螺旋丸（终极技）");
                }
                jinengnowshanghai.setText(""+player.getjineng6()*200);
                jinengnextshanghai.setText(""+(player.getjineng6()+1)*200);
                if(player.getjineng6()==3)jinengnextshanghai.setText("已满级");
                break;
            }
            default:{

            }
        }
    }
    protected void jinengup(){
        if(player.getJinengdian()<2)
            return;
        switch (jinengupnow){
            case 1:{
                if(player.getjineng1()==1||player.getjineng1()==2){
                    player.setJinengdian(player.getJinengdian()-2);
                    player.setJineng(player.getJineng()+1);
                }
                break;
            }
            case 2:{
                if(player.getjineng2()==1||player.getjineng2()==2){
                    player.setJinengdian(player.getJinengdian()-2);
                    player.setJineng(player.getJineng()+10);
                }
                break;
            }
            case 3:{
                if(player.getjineng3()==1||player.getjineng3()==2){
                    player.setJinengdian(player.getJinengdian()-2);
                    player.setJineng(player.getJineng()+100);
                }
                break;
            }
            case 4:{
                if(player.getjineng4()==1||player.getjineng4()==2){
                    player.setJinengdian(player.getJinengdian()-2);
                    player.setJineng(player.getJineng()+1000);
                }
                break;
            }
            case 5:{
                if(player.getjineng5()==1||player.getjineng5()==2){
                    player.setJinengdian(player.getJinengdian()-2);
                    player.setJineng(player.getJineng()+10000);
                }
                break;
            }
            case 6:{
                if(player.getjineng6()==1||player.getjineng6()==2){
                    player.setJinengdian(player.getJinengdian()-2);
                    player.setJineng(player.getJineng()+100000);
                }
                break;
            }
            default:{

            }
        }
    }
    protected void baocun(){
        Information information = new Information();
        information.setJingyan(player.getJingyan());
        information.setDengji(player.getDengji());
        information.setJinengdian(player.getJinengdian());
        information.setJineng(player.getJineng());
        information.setZhangjie(player.getZhangjie());
        information.update(player.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException var1){

            }
        });
    }
    @Override
    protected void onStop(){
        // TODO Auto-generated method stub
        stopService(musicintent);
        super.onStop();
    }
}
