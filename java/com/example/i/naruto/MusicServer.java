package com.example.i.naruto;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.i.naruto.R;

public class MusicServer extends Service {
    public MediaPlayer mediaPlayer;
    @Override
    public IBinder onBind(Intent intent) {
// TODO Auto-generated method stub
        return null;
    }
    @Override
    public void onStart(Intent intent,int startId){
        super.onStart(intent, startId);
        if(mediaPlayer==null){
// R.raw.mmp是资源文件，MP3格式的
            mediaPlayer = MediaPlayer.create(this, R.raw.login);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
    }
    @Override
    public void onDestroy() {
// TODO Auto-generated method stub
        super.onDestroy();
        mediaPlayer.stop();
    }
}