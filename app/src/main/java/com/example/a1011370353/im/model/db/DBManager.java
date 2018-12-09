package com.example.a1011370353.im.model.db;

import android.content.Context;

import com.example.a1011370353.im.model.dao.ContactTableDao;
import com.example.a1011370353.im.model.dao.InviteTableDao;

/**
 * Created by 1011370353 on 2018/12/9.
 */
//联系人表和邀请人信息表的操作类的管理对象
public class DBManager {

    private final DBHelper dbHelper;
    private final ContactTableDao contactTableDao;
    private final InviteTableDao inviteTableDao;


    public DBManager(Context context, String name) {
        //创建数据库
        dbHelper = new DBHelper(context, name);
        //创建数据库两张表的操作类
        contactTableDao = new ContactTableDao(dbHelper);
        inviteTableDao = new InviteTableDao(dbHelper);

    }
    //获取联系人表的操作对象
    public ContactTableDao contactTableDao (){
        return contactTableDao;
    }
    //获取邀请信息表的操作对象
    public InviteTableDao inviteTableDao(){
        return inviteTableDao;
    }
    //关闭数据库
    public void close() {
        dbHelper.close();
    }
}
