package com.example.i.naruto;

/**
 * Created by 或跃在渊i on 2017/11/11.
 */

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IOUser {
    ObjectOutputStream fo;
    ObjectInputStream fi;
    public void write(User user,File file){
        try {
            file.createNewFile();
            fo=new ObjectOutputStream(new FileOutputStream(file));
            fo.writeObject(user);
            fo.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public User read(File file) {
        try {
            fi=new ObjectInputStream(new FileInputStream(file));
            User user;
            try {

                user = (User)fi.readObject();
                return user;
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            fi.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}

