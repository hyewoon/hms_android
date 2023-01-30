package com.example.androidhms.reception.vo;

import com.example.androidhms.util.Util;

import java.io.Serializable;
import java.sql.Date;

public class StaffVO implements Serializable {

    private int staff_id, staff_level, department_id;
    private String name, department_name, lastChatCheckTime, social_id, email, phone_number, gender, introduction;
    private Date hire_date;

    public StaffVO(int staff_id, int staff_level, int department_id, String name, String department_name,
                   String lastChatCheckTime, String social_id, String email, String phone_number,
                   String gender, String introduction, Date hire_date) {
        this.staff_id = staff_id;
        this.staff_level = staff_level;
        this.department_id = department_id;
        this.name = name;
        this.department_name = department_name;
        this.lastChatCheckTime = lastChatCheckTime;
        this.social_id = social_id;
        this.email = email;
        this.phone_number = phone_number;
        this.gender = gender;
        this.introduction = introduction;
        this.hire_date = hire_date;
        lastChatCheckTime = null;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public int getStaff_level() {
        return staff_level;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public String getSocial_id() {
        return social_id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getGender() {
        return gender;
    }

    public String getIntroduction() {
        return introduction;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public String getLastChatCheckTime() {
        return lastChatCheckTime;
    }

    public void setLastChatCheckTime() {
        lastChatCheckTime = Util.getChatTimeStamp();
    }
}