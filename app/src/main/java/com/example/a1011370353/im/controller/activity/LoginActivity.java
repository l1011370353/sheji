package com.example.a1011370353.im.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a1011370353.im.R;
import com.example.a1011370353.im.model.Model;
import com.example.a1011370353.im.model.beam.UserInfo;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class LoginActivity extends Activity implements View.OnClickListener {
    private EditText etLoginName;
    private EditText etLoginPwd;
    private Button btLoginRegist;
    private Button btLoginLogin;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-12-07 11:43:02 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();

    }
    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-12-07 11:43:02 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        etLoginName = (EditText)findViewById( R.id.et_login_name );
        etLoginPwd = (EditText)findViewById( R.id.et_login_pwd );
        btLoginRegist = (Button)findViewById( R.id.bt_login_regist );
        btLoginLogin = (Button)findViewById( R.id.bt_login_login );

        btLoginRegist.setOnClickListener( this );
        btLoginLogin.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2018-12-07 11:43:02 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == btLoginRegist ) {//注册按钮
            // Handle clicks for btLoginRegist
            regist();
        } else if ( v == btLoginLogin ) {//登入按钮
            // Handle clicks for btLoginLogin
            login();

        }
    }
    //登入业务逻辑处理
    private void login() {
        //1. 获取输入的用户名和密码
        final String registName = etLoginName.getText().toString();
        final String registPwd = etLoginPwd.getText().toString();
        //        2.检验注册用户密码
        if(TextUtils.isEmpty(registName)||TextUtils.isEmpty(registPwd)){
            Toast.makeText(LoginActivity.this,"输入的用户名或密码不能为空",Toast.LENGTH_SHORT).show();
            return;

        }
        //登入逻辑处理
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //去环信服务器登入

                EMClient.getInstance().login(registName, registPwd, new EMCallBack() {
                    //登入成功后的处理
                    @Override
                    public void onSuccess() {
                        //对模型层的处理
                        Model.getInstance().loginSuccess();
                        //保存用户账号信息带本地数据库
                        Model.getInstance().getUserAccountDao().addAccount(new UserInfo(registName));

                        //提示登入成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this,"登入成功",Toast.LENGTH_SHORT).show();
                                //跳转到主界面
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                    }
                    //登入失败的处理
                    @Override
                    public void onError(int i, final String s) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this,"登入失败"+s,Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                    //登入过程中的处理
                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }
        });
    }

    //注册业务逻辑处理
    private void regist() {
//        1.获取输入的用户名密码
         final String registName = etLoginName.getText().toString();
         final String registPwd = etLoginPwd.getText().toString();

        //        2.检验注册用户密码
        if(TextUtils.isEmpty(registName)||TextUtils.isEmpty(registPwd)){
            Toast.makeText(LoginActivity.this,"输入的用户名或密码不能为空",Toast.LENGTH_SHORT).show();
            return;

        }
// 3.去服务器注册账号
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {//开启子线程
            @Override
            public void run() {
                try {
                    //去环信服务器注册账号
                    EMClient.getInstance().createAccount(registName,registPwd);
                    //更新页面显示
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this,"注册成功",Toast.LENGTH_SHORT).show();

                        }
                    });
                } catch (final HyphenateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this,"注册失败"+e.toString(),Toast.LENGTH_SHORT).show();

                        }
                    });

                }

            }
        });
    }


}
