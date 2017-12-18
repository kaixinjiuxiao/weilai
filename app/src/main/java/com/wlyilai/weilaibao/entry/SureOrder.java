package com.wlyilai.weilaibao.entry;

import java.io.Serializable;

/**
 * @author: captain
 * Time:  2017/12/18 0018
 * Describe:
 */
public class SureOrder implements Serializable {

    /**
     * status : 1
     * data : {"uid":"198","total_price":0.1,"pay_num":"1","pay_price":0.1,"out_trade_type":0,"province":"湖南","city":"长沙市","country":"雨花区","address":"华晨双帆国际","mobile":"15000000000","realname":"张三","osn":"62017121811471271828","pay_status":0,"grid":"11"}
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

    public static class DataBean implements Serializable{
        /**
         * uid : 198
         * total_price : 0.1
         * pay_num : 1
         * pay_price : 0.1
         * out_trade_type : 0
         * province : 湖南
         * city : 长沙市
         * country : 雨花区
         * address : 华晨双帆国际
         * mobile : 15000000000
         * realname : 张三
         * osn : 62017121811471271828
         * pay_status : 0
         * grid : 11
         */

        private String uid;
        private double total_price;
        private String pay_num;
        private double pay_price;
        private int out_trade_type;
        private String province;
        private String city;
        private String country;
        private String address;
        private String mobile;
        private String realname;
        private String osn;
        private int pay_status;
        private String grid;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public double getTotal_price() {
            return total_price;
        }

        public void setTotal_price(double total_price) {
            this.total_price = total_price;
        }

        public String getPay_num() {
            return pay_num;
        }

        public void setPay_num(String pay_num) {
            this.pay_num = pay_num;
        }

        public double getPay_price() {
            return pay_price;
        }

        public void setPay_price(double pay_price) {
            this.pay_price = pay_price;
        }

        public int getOut_trade_type() {
            return out_trade_type;
        }

        public void setOut_trade_type(int out_trade_type) {
            this.out_trade_type = out_trade_type;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getOsn() {
            return osn;
        }

        public void setOsn(String osn) {
            this.osn = osn;
        }

        public int getPay_status() {
            return pay_status;
        }

        public void setPay_status(int pay_status) {
            this.pay_status = pay_status;
        }

        public String getGrid() {
            return grid;
        }

        public void setGrid(String grid) {
            this.grid = grid;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "uid='" + uid + '\'' +
                    ", total_price=" + total_price +
                    ", pay_num='" + pay_num + '\'' +
                    ", pay_price=" + pay_price +
                    ", out_trade_type=" + out_trade_type +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", country='" + country + '\'' +
                    ", address='" + address + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", realname='" + realname + '\'' +
                    ", osn='" + osn + '\'' +
                    ", pay_status=" + pay_status +
                    ", grid='" + grid + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SureOrder{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
