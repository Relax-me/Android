package com.example.i.naruto;

import cn.bmob.v3.BmobUser;

/**
 * Created by 或跃在渊i on 2017/11/8.
 */

public class MyUser extends BmobUser {

    private String nicheng;

    public String getPlayer() {
        return nicheng;
    }

    public void setPlayer(String nicheng) {
        this.nicheng = nicheng;
    }
}
