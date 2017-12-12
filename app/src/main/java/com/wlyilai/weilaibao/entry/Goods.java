package com.wlyilai.weilaibao.entry;

/**
 * @author: captain
 * Time:  2017/12/9 0009
 * Describe:
 */
public class Goods {
    private String img;
    private String name;
    private String oldPrice;
    private String number;
    private String newPrice;

    public Goods(String img, String name, String oldPrice, String number, String newPrice) {
        this.img = img;
        this.name = name;
        this.oldPrice = oldPrice;
        this.number = number;
        this.newPrice = newPrice;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }
}
