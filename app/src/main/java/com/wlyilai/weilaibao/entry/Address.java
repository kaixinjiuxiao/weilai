package com.wlyilai.weilaibao.entry;

/**
 * Author : Captain
 * Time : 2017/12/4
 * Describe :
 */

public class Address {
    private String name;
    private String phone;
    private String address;
    private int code;
    private boolean isSelected;
    public Address(String name, String phone, String address, int code) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.code = code;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
