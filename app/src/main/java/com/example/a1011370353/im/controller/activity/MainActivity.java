package com.example.a1011370353.im.controller.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.RadioGroup;

import com.example.a1011370353.im.R;
import com.example.a1011370353.im.controller.fragment.ChatFragment;
import com.example.a1011370353.im.controller.fragment.ContactListFragment;
import com.example.a1011370353.im.controller.fragment.SettingFragment;

public class MainActivity extends FragmentActivity {
    private RadioGroup rg_main;
    private ChatFragment chatFragment;
    private ContactListFragment contactListFragment;
    private SettingFragment settingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intView();
        initDate();
        initListener();
    }
    //初始化RadioGroup的监听
    private void initListener() {
            rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    //创建Fragment
                   /* ChatFragment fragment1 = null;
                    ContactListFragment fragment2 =null;
                    SettingFragment fragment3 =null;*/
                    int fragmentID = 0;
                    switch (i){
                        //会话列表页面
                        case R.id.rb_main_chat:
                            fragmentID =1;
                            break;
                        //联系人界面
                        case R.id.rb_main_contact:
                           fragmentID =2;
                            break;
                        //设置界面
                        case R.id.rb_main_setting:
                            fragmentID =3;
                            break;
                    }
                    //实现fragment的切换
                    switchFragment(fragmentID);
                }
            });
            //默认选择聊天界面
            rg_main.check(R.id.rb_main_chat);
    }
    //实现fragment切换
    private void switchFragment(int fragmentID) {
        FragmentManager fragmentManager= getSupportFragmentManager();
        switch (fragmentID){
            case 1:
                fragmentManager.beginTransaction().replace(R.id.fl_main,chatFragment).commit();
                Log.d("TAG","fragmentID1");
                break;
            case 2:
                fragmentManager.beginTransaction().replace(R.id.fl_main,contactListFragment).commit();
                Log.d("TAG","fragmentID2");
                break;
            case 3:
                fragmentManager.beginTransaction().replace(R.id.fl_main,settingFragment).commit();
                Log.d("TAG","fragmentID3");

                break;
        }




    }

    private void initDate() {
        //创建 三个Fragment对象
        chatFragment = new ChatFragment();
        contactListFragment = new ContactListFragment();
        settingFragment = new SettingFragment();


    }

    private void intView() {
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        
    }
}
