package com.example.a1011370353.im.model.beam;

/**
 * Created by 1011370353 on 2018/12/9.
 */
//群信息的bean类
public class GroupInfo {
    private String groupName;//群名称
    private String groupID;//群id
    private String invatePerson;//邀请人

    public GroupInfo(String groupName, String groupID, String invatePerson) {
        this.groupName = groupName;
        this.groupID = groupID;
        this.invatePerson = invatePerson;
    }

    public GroupInfo() {
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getInvatePerson() {
        return invatePerson;
    }

    public void setInvatePerson(String invatePerson) {
        this.invatePerson = invatePerson;
    }
}
