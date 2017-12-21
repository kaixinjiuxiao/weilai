package com.wlyilai.weilaibao.entry;

/**
 * @author: captain
 * Time:  2017/12/21 0021
 * Describe:
 */
public class RedPNote {
    private String name;
    private String number;
    private String time;
    private String status;

    public RedPNote(String name, String number, String time, String status) {
        this.name = name;
        this.number = number;
        this.time = time;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
