package com.example.a1011370353.im.model.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.a1011370353.im.model.beam.GroupInfo;
import com.example.a1011370353.im.model.beam.InvationInfo;
import com.example.a1011370353.im.model.beam.UserInfo;
import com.example.a1011370353.im.model.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1011370353 on 2018/12/9.
 */
//邀请信息的操作类
public class InviteTableDao {
    public DBHelper  mHelper;
    public InviteTableDao(DBHelper helper){
        mHelper = helper;
    }
    // 添加邀请
    public void addInvitation(InvationInfo invationInfo){
        //获取数据库连接
        SQLiteDatabase db = mHelper.getReadableDatabase();


        //执行添加语句
        ContentValues values = new ContentValues();
        values.put(InviteTable.COL_REASON,invationInfo.getReason());//原因
        values.put(InviteTable.COL_STATUS,invationInfo.getSatus().ordinal());//状态 ordinal枚举类型的序号为int类型

        UserInfo user = invationInfo.getUser();
        if (user != null){//如果邀请信息里有联系人则认为是联系人添加
            values.put(InviteTable.COL_USER_HXID,user.getHxid());
            values.put(InviteTable.COL_USER_NAME,user.getName());


        }else {//如果邀请信息里没有联系人则认为是群组
            values.put(InviteTable.COL_GROUP_HXID,invationInfo.getGroup().getGroupID());
            values.put(InviteTable.COL_GROUP_NAME,invationInfo.getGroup().getGroupName());
            //可能有bug，如果同一个邀请一个人分别进入两个群，但主键HXId可能一眼
            values.put(InviteTable.COL_USER_HXID,invationInfo.getGroup().getInvatePerson());//数据库中以HXid为主键，群组没有hxid但有邀请的ID
        }

        db.replace(InviteTable.TAB_NAME,null,values);
        db.close();

    }

    // 获取所有邀请信息
    public List<InvationInfo> getInvitations(){
        //获取数据库连接
        SQLiteDatabase db = mHelper.getReadableDatabase();
        //执行查询语句
        String sql = " select * from " +InviteTable.TAB_NAME;

        Cursor cursor = db.rawQuery(sql, null);
        List<InvationInfo> invationInfos = new ArrayList<>();
        while (cursor.moveToNext()){
            InvationInfo invationInfo = new InvationInfo();
            invationInfo.setReason(cursor.getString(cursor.getColumnIndex(InviteTable.COL_REASON)));//原因
            //因为invationfo中的status 中存的是枚举类型所以要转换
            invationInfo.setSatus(int2InviteStatus(cursor.getInt(cursor.getColumnIndex(InviteTable.COL_STATUS))));
            String groupId = cursor.getString(cursor.getColumnIndex(InviteTable.COL_GROUP_HXID));
            if (groupId == null){//联系人的邀请信息
                UserInfo userInfo = new UserInfo();
                userInfo.setHxid(cursor.getString(cursor.getColumnIndex(InviteTable.COL_USER_HXID)));
                userInfo.setName(cursor.getString(cursor.getColumnIndex(InviteTable.COL_USER_NAME)));
                //需要改的地方，将nick的值和name一样
                userInfo.setNick(cursor.getString(cursor.getColumnIndex(InviteTable.COL_USER_NAME)));

            }else {//群组的邀请信息
                GroupInfo groupInfo = new GroupInfo();
                groupInfo.setGroupID(cursor.getString(cursor.getColumnIndex(InviteTable.COL_GROUP_HXID)));
                groupInfo.setGroupName(cursor.getString(cursor.getColumnIndex(InviteTable.COL_GROUP_NAME)));
                // 本页代码45行，存InvatePerson用的是COL_USER_HXID
                groupInfo.setInvatePerson(cursor.getString(cursor.getColumnIndex(InviteTable.COL_USER_HXID)));

            }
            //添加本次循环的邀请信息到总的集合中
            invationInfos.add(invationInfo);

        }

        //关闭资源
        cursor.close();
        db.close();
        //返回数据
        return invationInfos;



    }

    // 将int类型状态转换为邀请的状态
    private InvationInfo.InvitationStatus int2InviteStatus(int intStatus){
        if (intStatus == InvationInfo.InvitationStatus.NEW_INVITE.ordinal()) {
            return InvationInfo.InvitationStatus.NEW_INVITE;
        }

        if (intStatus == InvationInfo.InvitationStatus.INVITE_ACCEPT.ordinal()) {
            return InvationInfo.InvitationStatus.INVITE_ACCEPT;
        }

        if (intStatus == InvationInfo.InvitationStatus.INVITE_ACCEPT_BY_PEER.ordinal()) {
            return InvationInfo.InvitationStatus.INVITE_ACCEPT_BY_PEER;
        }

        if (intStatus == InvationInfo.InvitationStatus.NEW_GROUP_INVITE.ordinal()) {
            return InvationInfo.InvitationStatus.NEW_GROUP_INVITE;
        }

        if (intStatus == InvationInfo.InvitationStatus.NEW_GROUP_APPLICATION.ordinal()) {
            return InvationInfo.InvitationStatus.NEW_GROUP_APPLICATION;
        }

        if (intStatus == InvationInfo.InvitationStatus.GROUP_INVITE_ACCEPTED.ordinal()) {
            return InvationInfo.InvitationStatus.GROUP_INVITE_ACCEPTED;
        }

        if (intStatus == InvationInfo.InvitationStatus.GROUP_APPLICATION_ACCEPTED.ordinal()) {
            return InvationInfo.InvitationStatus.GROUP_APPLICATION_ACCEPTED;
        }

        if (intStatus == InvationInfo.InvitationStatus.GROUP_INVITE_DECLINED.ordinal()) {
            return InvationInfo.InvitationStatus.GROUP_INVITE_DECLINED;
        }

        if (intStatus == InvationInfo.InvitationStatus.GROUP_APPLICATION_DECLINED.ordinal()) {
            return InvationInfo.InvitationStatus.GROUP_APPLICATION_DECLINED;
        }

        if (intStatus == InvationInfo.InvitationStatus.GROUP_ACCEPT_INVITE.ordinal()) {
            return InvationInfo.InvitationStatus.GROUP_ACCEPT_INVITE;
        }

        if (intStatus == InvationInfo.InvitationStatus.GROUP_ACCEPT_APPLICATION.ordinal()) {
            return InvationInfo.InvitationStatus.GROUP_ACCEPT_APPLICATION;
        }

        if (intStatus == InvationInfo.InvitationStatus.GROUP_REJECT_APPLICATION.ordinal()) {
            return InvationInfo.InvitationStatus.GROUP_REJECT_APPLICATION;
        }

        if (intStatus == InvationInfo.InvitationStatus.GROUP_REJECT_INVITE.ordinal()) {
            return InvationInfo.InvitationStatus.GROUP_REJECT_INVITE;
        }

        return null;


    }

    // 删除邀请
    public void removeInvitation(String hxId){
        if (hxId == null){
            return;
        }
        //获取数据库连接
        SQLiteDatabase db = mHelper.getReadableDatabase();

        //执行删除语句
        db.delete(InviteTable.TAB_NAME,InviteTable.COL_GROUP_HXID +"=?",new String[]{hxId});
        db.close();
    }

    // 更新邀请状态
    public void updateInvitationStatus(InvationInfo.InvitationStatus invitationStatus, String hxId){
        if (hxId == null){
            return;
        }
        //获取数据库连接
        SQLiteDatabase db = mHelper.getReadableDatabase();
        //执行更新操作
        ContentValues valus = new ContentValues();
        valus.put(InviteTable.COL_STATUS,invitationStatus.ordinal());
        db.update(InviteTable.TAB_NAME,valus,InviteTable.COL_USER_HXID + "=?",new String[]{hxId});
        db.close();


    }
}
