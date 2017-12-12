package com.wlyilai.weilaibao.entry;

/**
 * @author: captain
 * Time:  2017/12/12 0012
 * Describe:
 */
public class MyGroup {
    private String img;
    private String orderCode;
    private String orderStatus;
    private String goodsName;
    private String goodsPrice;
    private String goodsNumber;
    private String goodsTotalNumber;
    private String goodsTotalPrice;


    public MyGroup(String img, String orderCode, String orderStatus, String goodsName,
                   String goodsPrice, String goodsNumber, String goodsTotalNumber, String goodsTotalPrice) {
        this.img = img;
        this.orderCode = orderCode;
        this.orderStatus = orderStatus;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.goodsNumber = goodsNumber;
        this.goodsTotalNumber = goodsTotalNumber;
        this.goodsTotalPrice = goodsTotalPrice;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getGoodsTotalNumber() {
        return goodsTotalNumber;
    }

    public void setGoodsTotalNumber(String goodsTotalNumber) {
        this.goodsTotalNumber = goodsTotalNumber;
    }

    public String getGoodsTotalPrice() {
        return goodsTotalPrice;
    }

    public void setGoodsTotalPrice(String goodsTotalPrice) {
        this.goodsTotalPrice = goodsTotalPrice;
    }

    @Override
    public String toString() {
        return "MyGroup{" +
                "img='" + img + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrice='" + goodsPrice + '\'' +
                ", goodsNumber='" + goodsNumber + '\'' +
                ", goodsTotalNumber='" + goodsTotalNumber + '\'' +
                ", goodsTotalPrice='" + goodsTotalPrice + '\'' +
                '}';
    }
}
