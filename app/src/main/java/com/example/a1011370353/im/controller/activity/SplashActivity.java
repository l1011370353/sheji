package com.example.a1011370353.im.controller.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.a1011370353.im.R;
import com.example.a1011370353.im.model.Model;
import com.example.a1011370353.im.model.beam.UserInfo;
import com.hyphenate.chat.EMClient;

public class SplashActivity extends Activity {
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(isFinishing()){
                return;
            }
            toMainOrLogin();

        }
    };
    private void toMainOrLogin() {
      /* new Thread(){
           @Override
           public void run() {


           }
       }.start();*/
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                if(EMClient.getInstance().isLoggedInBefore()){//登入过

                    //跳转之前获取当前登入用户的信息
                    UserInfo account = Model.getInstance().getUserAccountDao().getAccountByHxID(EMClient.getInstance().getCurrentUser());
                    if (account ==null){

                        //跳转到登入页面
                        Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }else {
                        // 登录成功后的方法
                        Model.getInstance().loginSuccess(account);
                        //跳转到主界面
                        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                        startActivity(intent);
                    }




                }else{//没有登入过
                    //跳转到登入页面
                    Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
                //结束当前页面
                finish();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //发送消息
        handler.sendMessageDelayed(Message.obtain(),2000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
