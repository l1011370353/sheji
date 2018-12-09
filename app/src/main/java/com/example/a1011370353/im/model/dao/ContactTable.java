package com.example.a1011370353.im.model.dao;

/**
 * Created by 1011370353 on 2018/12/9.
 */
//创建联系人表
public class ContactTable {
    public static final String TAB_NAME="tab_contact";

    public static final String COL_HXID="hxid";
    public static final String COL_NAME="name";
    public static final String COL_NICK="nick";
    public static final String COL_PHOTO="photo";

    public  static final String COL_IS_CONTACT="is_contact"; //是否是联系人群组中可能某些人不是你的好友

    public static final String CREATE_TAB ="create table "
            + TAB_NAME + " ("
            + COL_HXID + " text primary key,"
            + COL_NAME + " text,"
            + COL_NICK + " text,"
            + COL_PHOTO +" text,"
            + COL_IS_CONTACT + " integer);";



}
