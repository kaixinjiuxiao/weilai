package com.wlyilai.weilaibao.entry;

/**
 * Author : Captain
 * Time : 2017/12/12
 * Describe :
 */

public class JoinInfo {
    private String phone;
    private String time;

    public JoinInfo(String phone, String time) {
        this.phone = phone;
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
