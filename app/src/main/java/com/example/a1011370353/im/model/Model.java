package com.example.a1011370353.im.model;

import android.content.Context;

import com.example.a1011370353.im.model.dao.UserAccountDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 1011370353 on 2018/12/7.
 */
//数据模型全局类
public class Model {
    private Context mContxt;
    private ExecutorService executors = Executors.newCachedThreadPool();

    //创建对象
    private static Model model = new Model();
    private UserAccountDao userAccountDao;

    //私有化构造
    private Model(){

    }
    //获取单例对象
    public static  Model getInstance(){
        return model;
    }
    //初始化方法
    public void init(Context context){
        mContxt = context;
        //创建用户账号数据库操作类对象
        userAccountDao = new UserAccountDao(mContxt);
    }
    //获取全局线程池
    public ExecutorService getGlobalThreadPool(){
        return executors;
    }
    //用户登入成功后的处理方法
    public void loginSuccess() {

    }
    //获取用户账号数据库的操作类对象
    public UserAccountDao getUserAccountDao(){
        return userAccountDao;
    }
}
