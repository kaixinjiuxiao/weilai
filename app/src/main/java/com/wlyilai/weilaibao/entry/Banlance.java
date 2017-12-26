package com.wlyilai.weilaibao.entry;

/**
 * @author: captain
 * Time:  2017/12/25 0025
 * Describe:
 */
public class Banlance {
    private String details;
    private String number;
    private String time;

    public Banlance(String details, String number, String time) {
        this.details = details;
        this.number = number;
        this.time = time;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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
}
