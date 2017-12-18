package com.wlyilai.weilaibao.entry;

import java.io.Serializable;

/**
 * @author: captain
 * Time:  2017/12/18 0018
 * Describe:
 */
public class SureBuy implements Serializable {

    /**
     * status : 1
     * data : {"gname":"商品-4","gteam_price":"0.10","gimg":"http://test.mgbh.wlylai.com./Uploads/GoodsSupply/2017-12-15/df52a97c3afd6b918960.jpeg","gpay_num":"0","gnum":"10","gpay_limit":"1","id":"11","buy_num":"1","total_price":0.1}
     */

    private int status;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * gname : 商品-4
         * gteam_price : 0.10
         * gimg : http://test.mgbh.wlylai.com./Uploads/GoodsSupply/2017-12-15/df52a97c3afd6b918960.jpeg
         * gpay_num : 0
         * gnum : 10
         * gpay_limit : 1
         * id : 11
         * buy_num : 1
         * total_price : 0.1
         */

        private String gname;
        private String gteam_price;
        private String gimg;
        private String gpay_num;
        private String gnum;
        private String gpay_limit;
        private String id;
        private String buy_num;
        private double total_price;

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public String getGteam_price() {
            return gteam_price;
        }

        public void setGteam_price(String gteam_price) {
            this.gteam_price = gteam_price;
        }

        public String getGimg() {
            return gimg;
        }

        public void setGimg(String gimg) {
            this.gimg = gimg;
        }

        public String getGpay_num() {
            return gpay_num;
        }

        public void setGpay_num(String gpay_num) {
            this.gpay_num = gpay_num;
        }

        public String getGnum() {
            return gnum;
        }

        public void setGnum(String gnum) {
            this.gnum = gnum;
        }

        public String getGpay_limit() {
            return gpay_limit;
        }

        public void setGpay_limit(String gpay_limit) {
            this.gpay_limit = gpay_limit;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBuy_num() {
            return buy_num;
        }

        public void setBuy_num(String buy_num) {
            this.buy_num = buy_num;
        }

        public double getTotal_price() {
            return total_price;
        }

        public void setTotal_price(double total_price) {
            this.total_price = total_price;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "gname='" + gname + '\'' +
                    ", gteam_price='" + gteam_price + '\'' +
                    ", gimg='" + gimg + '\'' +
                    ", gpay_num='" + gpay_num + '\'' +
                    ", gnum='" + gnum + '\'' +
                    ", gpay_limit='" + gpay_limit + '\'' +
                    ", id='" + id + '\'' +
                    ", buy_num='" + buy_num + '\'' +
                    ", total_price=" + total_price +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SureBuy{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
