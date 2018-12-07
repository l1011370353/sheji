package com.example.a1011370353.im.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a1011370353.im.model.beam.UserInfo;
import com.example.a1011370353.im.model.db.UserAccountDB;

/**
 * Created by 1011370353 on 2018/12/7.
 */
//用户账号数据库的操作类
public class UserAccountDao {

    private final UserAccountDB mHelper;

    public UserAccountDao(Context context){
        mHelper = new UserAccountDB(context);

    }
    //添加用户到数据库
    public void addAccount(UserInfo user){
        //获取数据库对象
        SQLiteDatabase db = mHelper.getReadableDatabase();
        //执行添加操作
        ContentValues values = new ContentValues();
        values.put(UserAccountTable.COL_HXID,user.getHxid());
        values.put(UserAccountTable.COL_NAME,user.getName());
        values.put(UserAccountTable.COL_NICK,user.getNick());
        values.put(UserAccountTable.COL_PHOTO,user.getPhoto());
        db.replace(UserAccountTable.TAB_NAME,null,values);  //此方法如果数据库有就直接替换，没有就添加

    }
    //根据环信id获取所有用户信息
    public UserInfo getAccountByHxID(String hxID){
        //获取数据库对象
        SQLiteDatabase db = mHelper.getReadableDatabase();
        //执行查询语句
        String sql = "select * from "+UserAccountTable.TAB_NAME + " where " +UserAccountTable.COL_HXID+ "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{hxID});//根据的传递过来 hxid查询表
        UserInfo userInfo = null;
        if (cursor.moveToNext()){
            //封装对象
            userInfo = new UserInfo();
            //根据cursor的getString方法返回值，值是通过cursor的index确定，index为表中的属性
            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_HXID)));
            userInfo.setName(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_NAME)));
            userInfo.setNick(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_NAME)));
            userInfo.setPhoto(cursor.getString(cursor.getColumnIndex(UserAccountTable.COL_PHOTO)));


        }
        //关闭资源
        cursor.close();

        //返回数据

        return userInfo;

    }
}
