package com.example.a1011370353.im.model.beam;

/**
 * Created by 1011370353 on 2018/12/9.
 */
//邀请人的信息
public class InvationInfo {
    private UserInfo user; //联系人
    private GroupInfo group;//群组
    private String reason;//邀请原因
    private  InvitationStatus satus;//邀请状态
    //构造方法
    public InvationInfo() {
    }
    //有参的构造方法
    public InvationInfo(UserInfo user, GroupInfo group, String reason, InvitationStatus satus) {
        this.user = user;
        this.group = group;
        this.reason = reason;
        this.satus = satus;
    }

    public enum InvitationStatus{
        // 联系人邀请信息状态
        NEW_INVITE,// 新邀请
        INVITE_ACCEPT,//接受邀请
        INVITE_ACCEPT_BY_PEER,// 邀请被接受

        // --以下是群组邀请信息状态--

        //收到邀请去加入群
        NEW_GROUP_INVITE,

        //收到申请群加入
        NEW_GROUP_APPLICATION,

        //群邀请已经被对方接受
        GROUP_INVITE_ACCEPTED,

        //群申请已经被批准
        GROUP_APPLICATION_ACCEPTED,

        //接受了群邀请
        GROUP_ACCEPT_INVITE,

        //批准的群加入申请
        GROUP_ACCEPT_APPLICATION,

        //拒绝了群邀请
        GROUP_REJECT_INVITE,

        //拒绝了群申请加入
        GROUP_REJECT_APPLICATION,

        //群邀请被对方拒绝
        GROUP_INVITE_DECLINED,

        //群申请被拒绝
        GROUP_APPLICATION_DECLINED
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public GroupInfo getGroup() {
        return group;
    }

    public void setGroup(GroupInfo group) {
        this.group = group;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public InvitationStatus getSatus() {
        return satus;
    }

    public void setSatus(InvitationStatus satus) {
        this.satus = satus;
    }

    @Override
    public String toString() {
        return "InvationInfo{" +
                "user=" + user +
                ", group=" + group +
                ", reason='" + reason + '\'' +
                ", satus=" + satus +
                '}';
    }
}