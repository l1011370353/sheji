package com.example.a1011370353.im.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.a1011370353.im.R;
import com.example.a1011370353.im.controller.activity.LoginActivity;
import com.example.a1011370353.im.model.Model;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

/**
 * Created by 1011370353 on 2018/12/8.
 */
//设置页面
public class SettingFragment extends Fragment {
    private Button bt_seeting_out;
    //添加布局
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_setting, null);
        initview(view);
        return view;

    }

    private void initview(View view) {
        bt_seeting_out =(Button) view.findViewById(R.id.bt_setting_out);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDate();
    }

    private void initDate() {
        //在button显示当前用户名称
        bt_seeting_out.setText("退出登入("+ EMClient.getInstance().getCurrentUser()+")");
        //退出登入的逻辑
        bt_seeting_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        EMClient.getInstance().logout(false, new EMCallBack() {
                            //退出成功
                            @Override
                            public void onSuccess() {
                                //退出登入关闭DBHelper
                                Model.getInstance().getDbManager().close();

                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //更新UI显示
                                        Toast.makeText(getActivity(),"退出成功",Toast.LENGTH_SHORT).show();

                                        //回到登入页面
                                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                                        startActivity(intent);
                                        //结束当前页面
                                        getActivity().finish();
                                        }
                                    });

                            }
                            //退出失败
                            @Override
                            public void onError(int i, final String s) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //更新UI显示
                                        Toast.makeText(getActivity(),"退出失败"+s,Toast.LENGTH_SHORT).show();

                                    }
                                });

                            }
                            //退出进行中
                            @Override
                            public void onProgress(int i, String s) {

                            }
                        });
                    }
                });
            }
        });
    }
}
