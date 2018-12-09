package com.example.a1011370353.im.model.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a1011370353.im.model.beam.UserInfo;
import com.example.a1011370353.im.model.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1011370353 on 2018/12/9.
 */
//联系人表的操作类
public class ContactTableDao {
    private DBHelper mHelper;

    public ContactTableDao(DBHelper helper){
        mHelper=helper;
    }
    // 获取所有联系人
    public List<UserInfo> getContacts(){
        //获取数据库连接
        SQLiteDatabase db = mHelper.getReadableDatabase();
        //执行查询语句
        String sql = "select * from " + ContactTable.TAB_NAME +" where "+ContactTable.COL_IS_CONTACT+"=1";//1:是否是联系人，1是，0不是

        Cursor cursor = db.rawQuery(sql, null);
        List<UserInfo> users = new ArrayList<>();
        while (cursor.moveToNext()){
            UserInfo userInfo = new UserInfo();
            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(ContactTable.COL_HXID)));
            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(ContactTable.COL_NAME)));
            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(ContactTable.COL_NICK)));
            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(ContactTable.COL_PHOTO)));
            users.add(userInfo);
        }

        //关闭资源
        cursor.close();
        db.close();

        //返回数据
        return users;
    }

    // 通过环信id获取联系人单个信息
    public UserInfo getContactByHx(String hxId){
        if (hxId==null){
            return null;
        }
        //获取数据库连接
        SQLiteDatabase db = mHelper.getReadableDatabase();
        //执行查询语句

        String sql = "select * from " +ContactTable.TAB_NAME + " where " + ContactTable.COL_HXID + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{hxId});
        UserInfo userInfo = null;
        //遍历cuosor
        if (cursor.moveToNext()){
            userInfo = new UserInfo();
            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(ContactTable.COL_HXID)));
            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(ContactTable.COL_NAME)));
            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(ContactTable.COL_NICK)));
            userInfo.setHxid(cursor.getString(cursor.getColumnIndex(ContactTable.COL_PHOTO)));

        }
        //关闭资源
        cursor.close();;
        db.close();

        //返回数据
        return userInfo;
    }

    // 通过环信id获取多个用户联系人信息
    public List<UserInfo> getContactsByHx(List<String> hxIds){
        if (hxIds==null||hxIds.size()<=0){
            return null;
        }
        List<UserInfo> contacts = new ArrayList<>();
        //遍历hxIDs,来查找
        for (String hxid: hxIds){//1遍历数据的类型，2，类似于int i 3：遍历的数组
            UserInfo contact = getContactByHx(hxid);
            contacts.add(contact);
        }

        return contacts;


    }

    // 保存单个联系人
    public void saveContact(UserInfo user, boolean isMyContact){
        if (user == null){
            return;
        }
        //  获取数据库连接
        SQLiteDatabase db = mHelper.getReadableDatabase();//position
        //执行保存语句
        ContentValues values = new ContentValues();
        values.put(ContactTable.COL_HXID,user.getHxid());
        values.put(ContactTable.COL_NAME,user.getName());
        values.put(ContactTable.COL_NICK,user.getNick());
        values.put(ContactTable.COL_PHOTO,user.getPhoto());
        values.put(ContactTable.COL_IS_CONTACT,isMyContact? 1:0);
        db.replace(ContactTable.TAB_NAME,null,values);
        db.close();
    }


    // 保存多个联系人信息
    public void saveContacts(List<UserInfo> contacts, boolean isMyContact){
        if(contacts ==null||contacts.size()<=0){
            return;
        }
         for(UserInfo contact:contacts){
            saveContact(contact,isMyContact);
         }
    }

    // 删除联系人信息
    public void deleteContactByHxId(String hxId){
        if (hxId==null){
            SQLiteDatabase db = mHelper.getReadableDatabase();
            db.delete(ContactTable.TAB_NAME,ContactTable.COL_HXID+"=?",new String[]{hxId});
            db.close();
        }
    }

}
