package com.example.i.naruto;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class FightActivity extends AppCompatActivity {
    public static Resources res = null;
    public static FightActivity fightActivity = null;
    // 定义成员变量记录游戏窗口的宽度、高度
    public static int windowWidth;
    public static int windowHeight;
    public static FrameLayout mainLayout = null;
    public static FrameLayout.LayoutParams mainLP = null;
    // 游戏窗口的主游戏界面
    public static GameView mainView = null;
    public static rudderSurfaceView rudderView = null;
    // 播放背景音乐的MediaPlayer
    private MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        fightActivity = this;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics metric = new DisplayMetrics();
        // 获取屏幕高度、宽度
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        windowHeight = metric.heightPixels;  // 屏幕高度
        windowWidth = metric.widthPixels;  // 屏幕宽度
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        res = getResources();
        setContentView(R.layout.fight);
        mainView = new GameView(this);
        mainLayout = (FrameLayout) findViewById(R.id.fightframe);
        mainLP = new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        mainLayout.addView(mainView, mainLP);
        setListenerfight();
//        // 播放背景音乐
        player = MediaPlayer.create(this, R.raw.user);
        player.setLooping(true);
        player.start();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            showDialog(1);
            return true;
        } else
            return super.onKeyDown(keyCode, event);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 1) {
            return new AlertDialog.Builder(FightActivity.this)
                    .setMessage("是否退出程序?")
                    .setTitle("用户退出")
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.dismiss();
                                    android.os.Process
                                            .killProcess(android.os.Process
                                                    .myPid());
                                    finish();

                                }
                            })
                    .setNegativeButton("取消",
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.dismiss();

                                }
                            }).create();

        }
        return null;

    }
    @Override
    public void onResume()
    {
        super.onResume();
        // 当游戏暂停时，暂停播放背景音乐
        if(player != null && !player.isPlaying())
        {
            player.start();
        }
    }
    @Override
    public void onPause()
    {
        super.onPause();
        // 当游戏恢复时，如果没有播放背景音乐，开始播放背景音乐
        if(player != null && player.isPlaying())
        {
            player.pause();
        }
    }

    @Override
    public void finish() {
        player.stop();
        rudderSurfaceView.isStop=true;
        System.exit(0);
        super.finish();
    }
    //0-mapr----------x
    //730-1030--------y
    protected void left(){
        MyNaruto.L_R=false;
            GameView.zhujuenow=GameView.getzhujuel(MyNaruto.bitmap);
        if(MyNaruto.x>100){
            MyNaruto.x-=20;
        }else if(MyNaruto.mapx>0){
            MyNaruto.mapx-=20;
        }else if(MyNaruto.x>0){
            MyNaruto.x-=20;
        }
    }
    protected void right(){
        MyNaruto.L_R=true;
            GameView.zhujuenow=GameView.getzhujuer(MyNaruto.bitmap);
        if(MyNaruto.x<1350) {
            MyNaruto.x += 20;
        }else if(MyNaruto.mapx<(MyNaruto.mapr-1950)){
            MyNaruto.mapx+=20;
        }else if(MyNaruto.x<1550){
            MyNaruto.x += 20;
        }
    }
    protected void up(){
        if(MyNaruto.L_R){
            GameView.zhujuenow=GameView.getzhujuer(MyNaruto.bitmap);
        }else{
            GameView.zhujuenow=GameView.getzhujuel(MyNaruto.bitmap);
        }
        if (MyNaruto.y>730)
            MyNaruto.y-=10;
    }
    protected void down(){
        if(MyNaruto.L_R){
            GameView.zhujuenow=GameView.getzhujuer(MyNaruto.bitmap);
        }else{
            GameView.zhujuenow=GameView.getzhujuel(MyNaruto.bitmap);
        }
        if(MyNaruto.y<1030)
            MyNaruto.y+=10;
    }
    protected void setListenerfight(){
        rudderSurfaceView rudder = (rudderSurfaceView)findViewById(R.id.rudder);
        rudder.setRudderListener(new RudderListener() {
            @Override
            public void onSteeringWheelChanged(int action, int angle) {
                // TODO Auto-generated method stub
                //////////////////////////////
                if(MyNaruto.bitmap!=7){
                    MyNaruto.bitmap++;
                }else{
                    MyNaruto.bitmap=1;
                }
                if(23<angle&&angle<=68){
                    up();
                    right();
                }
                else if(68<angle&&angle<=113){
                    up();
                }
                else if(113<angle&&angle<=158){
                    up();
                    left();
                }
                else if(158<angle&&angle<=203){
                    left();
                }
                else if(203<angle&&angle<=248){
                    down();
                    left();
                }
                else if(248<angle&&angle<=293){
                    down();
                }
                else if(293<angle&&angle<=338){
                    down();
                    right();
                }
                else if(338<angle||angle<=23) {
                    right();
                }
            }
        });
    }
}
