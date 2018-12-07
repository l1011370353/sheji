package com.example.a1011370353.im.model.dao;

/**
 * Created by 1011370353 on 2018/12/7.
 */
//创建表语句
public class UserAccountTable {
    public static final String TAB_NAME = "tab_account";
    public static final String COL_NAME= "name";
    public static final String COL_HXID = "hxid";
    public static final String COL_NICK = "nick";
    public static final String COL_PHOTO = "photo";
    public static final String CREATE_TAB = "create table "
            + TAB_NAME +" ("
            + COL_NAME +" text primary key,"
            + COL_HXID +" text,"
            + COL_NICK +" text,"
            + COL_PHOTO +" text);";


}
