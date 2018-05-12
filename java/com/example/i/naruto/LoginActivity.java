package com.example.i.naruto;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class LoginActivity extends AppCompatActivity {
    private Player player;
    public static String nicheng;
    private Intent musicintent = new Intent("com.angel.Android.MUSIClogin");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        player=(Player)getApplication();
        startService(musicintent);
        Bmob.initialize(this, "09e8b9c6605b51cf30b2e370171342f1");
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            setContentView(R.layout.activity_login2);
                                                            CheckBox save=(CheckBox)findViewById(R.id.save);
                                                            save.setChecked(true);
                                                            Context appContext = getApplicationContext();
                                                            File file = new File(appContext.getFilesDir(),"user.txt");
                                                            IOUser iUser=new IOUser();
                                                            User user =new User();
                                                            user=iUser.read(file);
                                                            if(user!=null) {
                                                                EditText username = (EditText) findViewById(R.id.username);
                                                                EditText password = (EditText) findViewById(R.id.password);
                                                                username.setText(user.getUsername());
                                                                password.setText(user.getPassword());
                                                            }
                                                            findViewById(R.id.login2).setOnClickListener(new View.OnClickListener() {
                                                                                                             @Override
                                                                                                             public void onClick(View v) {
                                                                                                                 EditText username = (EditText) findViewById(R.id.username);
                                                                                                                 EditText password = (EditText) findViewById(R.id.password);
                                                                                                                 CheckBox save=(CheckBox)findViewById(R.id.save);
                                                                                                                 if(save.isChecked())
                                                                                                                 {
                                                                                                                     Context appContext = getApplicationContext();
                                                                                                                     File file = new File(appContext.getFilesDir(),"user.txt");
                                                                                                                     User user=new User();
                                                                                                                     user.setUsername(username.getText().toString());
                                                                                                                     user.setPassword(password.getText().toString());
                                                                                                                     IOUser oUser=new IOUser();
                                                                                                                     oUser.write(user,file);
                                                                                                                 }
                                                                                                                 LoginActivity.this.login(username.getText().toString(), password.getText().toString());
                                                                                                             }
                                                                                                         }
                                                            );
                                                            findViewById(R.id.zhuce).setOnClickListener(new View.OnClickListener() {
                                                                                                            @Override
                                                                                                            public void onClick(View v) {
                                                                                                                EditText username = (EditText) findViewById(R.id.username);
                                                                                                                EditText password = (EditText) findViewById(R.id.password);
                                                                                                                LoginActivity.this.Zhuce(username.getText().toString(), password.getText().toString());
                                                                                                            }
                                                                                                        }
                                                            );
                                                            findViewById(R.id.zhaohui).setOnClickListener(new View.OnClickListener() {
                                                                                                              @Override
                                                                                                              public void onClick(View v) {
                                                                                                                  EditText username = (EditText) findViewById(R.id.username);
                                                                                                                  LoginActivity.this.zhaohui(username.getText().toString());
                                                                                                              }
                                                                                                          }
                                                            );
                                                        }
                                                    }
        );
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        stopService(musicintent);
        super.onStop();
    }

    protected void Zhuce(String username, String password) {
        BmobUser bu = new BmobUser();
        bu.setUsername(username);
        bu.setPassword(password);
        bu.setEmail(username);
        bu.signUp(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e == null) {
                    Toast.makeText(LoginActivity.this, "邮件已发送，请查看邮件认证", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "请输入正确的邮箱账户和密码", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void login(String username, String password) {
        BmobUser bu2 = new BmobUser();
        bu2.setUsername(username);
        bu2.setPassword(password);
        bu2.login(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e == null) {
                    nicheng = (String) BmobUser.getObjectByKey("nicheng");
                    if (nicheng == null) {
                        setContentView(R.layout.activity_login3);
                        findViewById(R.id.jiance).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                jiancenicheng();
                            }
                        });
                    } else {
                        BmobQuery<Information> query = new BmobQuery<Information>();
                        query.addWhereEqualTo("nicheng", nicheng);
                        //执行查询方法
                        query.findObjects(new FindListener<Information>() {
                            @Override
                            public void done(List<Information> var1, BmobException var2) {
                                if (!var1.isEmpty()) {
                                    for (Information information:var1){
                                        player.setNicheng(nicheng);
                                        player.setDengji(information.getDengji());
                                        player.setJingyan(information.getJingyan());
                                        player.setZhangjie(information.getZhangjie());
                                        player.setJinengdian(information.getJinengdian());
                                        player.setJineng(information.getJineng());
                                        player.setObjectId(information.getObjectId());
                                    }
                                }
                            }
                        });
                        Intent intentlogin = new Intent(LoginActivity.this, UserActivity.class);
                        startActivity(intentlogin);
                        finish();
                    }
                    //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                    //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                } else {
                    Toast.makeText(LoginActivity.this, "账户名或密码不正确", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void zhaohui(final String username) {
        BmobUser.resetPasswordByEmail(username, new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(LoginActivity.this, "重置密码请求成功，请到" + username + "邮箱进行密码重置操作", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "账户名不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void jiancenicheng() {
        EditText setnicheng = (EditText) findViewById(R.id.setnicheng);
        BmobQuery<Information> query = new BmobQuery<Information>();
        //查询playerName叫“比目”的数据
        query.addWhereEqualTo("nicheng", setnicheng.getText().toString());
        //执行查询方法
        query.findObjects(new FindListener<Information>() {
            @Override
            public void done(List<Information> var1, BmobException var2) {
                if (!var1.isEmpty()) {
                        TextView jiancetip = (TextView) findViewById(R.id.jiancetip);
                        jiancetip.setText("对不起，该昵称已经注册！");
                    }
                else
                {
                    TextView jiancetip = (TextView) findViewById(R.id.jiancetip);
                    jiancetip.setText("恭喜，你可以使用！");
                    findViewById(R.id.queding).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            quedingnicheng();
                        }
                    });
                }

            }
        });
    }

    protected void quedingnicheng() {
        EditText setnicheng = (EditText) findViewById(R.id.setnicheng);
        nicheng = setnicheng.getText().toString();
        MyUser user = new MyUser();
        user.setPlayer(nicheng);
        BmobUser bmobUser = BmobUser.getCurrentUser();
        user.update(bmobUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException var1) {
                if (var1 == null) {
                    player.setNicheng(nicheng);
                    player.setDengji(1);
                    player.setJingyan(0);
                    player.setZhangjie(1);
                    player.setJinengdian(0);
                    player.setJineng(0);
                    Information information = new Information();
                    information.setNicheng(nicheng);
                    information.setDengji(1);
                    information.setJingyan(0);
                    information.setZhangjie(1);
                    information.setJinengdian(0);
                    information.setJineng(0);
                    player.setObjectId(information.getObjectId());
                    information.save(new SaveListener<String>() {
                        @Override
                        public void done(String objectId, BmobException e) {
                                Intent intentlogin = new Intent(LoginActivity.this, UserActivity.class);
                                startActivity(intentlogin);
                                finish();
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}