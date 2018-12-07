package com.example.a1011370353.im;

import android.app.Application;

import com.example.a1011370353.im.model.Model;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;

/**
 * Created by 1011370353 on 2018/12/7.
 */

public class IMApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(false);
        options.setAutoAcceptGroupInvitation(false);
        EaseUI.getInstance().init(this,options);
        //初始化数据模型层类
        Model.getInstance().init(this);
    }
}
