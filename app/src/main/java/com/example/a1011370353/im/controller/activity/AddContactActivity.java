package com.example.a1011370353.im.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1011370353.im.R;
import com.example.a1011370353.im.model.Model;
import com.example.a1011370353.im.model.beam.UserInfo;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

//添加联系人页面
public class AddContactActivity extends AppCompatActivity {
    private TextView tv_add_contact_search;
    private EditText et_add_contact_name;
    private RelativeLayout rl_add_contact;
    private  TextView tv_add_contact_name;
    private Button bt_add_contact_add;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        initview();//初始化布局
        initListener();
    }

    private void initListener() {
        //查找按钮的点击事件的处理
        tv_add_contact_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                find();
            }
        });
        //添加按钮的点击事件处理
        bt_add_contact_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TAG","添加按钮被点击了");
                add();

            }
        });

    }
//    查找按钮的处理
    private void find() {
        //获取用户输入的名称
        final String name = et_add_contact_name.getText().toString();
        //校验用户是否存在
        if (TextUtils.isEmpty(name)){
            Toast.makeText(AddContactActivity.this,"用户不能为空",Toast.LENGTH_SHORT).show();
            return;
        }

        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {

            @Override
            public void run() {
                //去服务器判读当前用户是否存在
                userInfo = new UserInfo(name);

                //更新UI显示
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rl_add_contact.setVisibility(View.VISIBLE);
                        tv_add_contact_name.setText(userInfo.getName());
                    }
                });
                }
        });




    }

    //添加按钮处理
    private void add() {
//        Bug：添加自己程序崩溃!
       /* String emUsername = EMClient.getInstance().getCurrentUser();
        Log.e("TAG","本人用户名 "+emUsername);
        String username =userInfo.getName();
        Log.e("TAG","要添加的添用户名 "+emUsername);
        if (emUsername.toString()==username.toString()){

            Log.e("TAG","if条件被执行");
            Toast.makeText(AddContactActivity.this,"添加好友不能为自己",Toast.LENGTH_SHORT).show();
            return;*/
//        }else{
            Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    //去环信服务器添加好友
                    try {
                        //两个参数，1：好友信息，2添加原因
                        EMClient.getInstance().contactManager().addContact(userInfo.getName(),"添加好友");
                        Log.e("TAG","发送添加好友消息成功"+userInfo.getName());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(AddContactActivity.this,"发送添加好友成功",Toast.LENGTH_SHORT).show();

                            }
                        });
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                        Toast.makeText(AddContactActivity.this,"发送添加好友失败"+e.toString(),Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

//    }

    private void initview() {
        tv_add_contact_search= (TextView) findViewById(R.id.tv_add_contact_search);
        et_add_contact_name = (EditText) findViewById(R.id.et_add_contact_name);
        rl_add_contact = (RelativeLayout) findViewById(R.id.rl_add_contact);
        tv_add_contact_name = (TextView) findViewById(R.id.tv_add_contact_name);
        bt_add_contact_add = (Button) findViewById(R.id.bt_add_contact_add);

    }
}
