package com.wlyilai.weilaibao.entry;

/**
 * @author: captain
 * Time:  2017/12/19 0019
 * Describe:
 */
public class OrderDetails {

    /**
     * status : 1
     * data : {"trade":"1","paytime":"2017-12-18 16:32:20","pay_price":"0.20","pay_num":"2","province":"湖南","city":"长沙市","country":"雨花区","realname":"张三","mobile":"15000000000","addtime":"2017-12-18 16:32:18","gname":"商品-4","gimg":"http://test.mgbh.wlylai.com./Uploads/GoodsSupply/2017-12-15/df52a97c3afd6b918960.jpeg","gteam_price":"0.10","trade_remark":"待发货"}
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

    public static class DataBean {
        /**
         * trade : 1
         * paytime : 2017-12-18 16:32:20
         * pay_price : 0.20
         * pay_num : 2
         * province : 湖南
         * city : 长沙市
         * country : 雨花区
         * realname : 张三
         * mobile : 15000000000
         * addtime : 2017-12-18 16:32:18
         * gname : 商品-4
         * gimg : http://test.mgbh.wlylai.com./Uploads/GoodsSupply/2017-12-15/df52a97c3afd6b918960.jpeg
         * gteam_price : 0.10
         * trade_remark : 待发货
         */
        private String osn;
        private String trade;
        private String paytime;
        private String pay_price;
        private String pay_num;
        private String province;
        private String city;
        private String country;
        private String realname;
        private String mobile;
        private String addtime;
        private String gname;
        private String gimg;
        private String address;

        public String getOsn() {
            return osn;
        }

        public void setOsn(String osn) {
            this.osn = osn;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        private String gteam_price;
        private String trade_remark;

        public String getTrade() {
            return trade;
        }

        public void setTrade(String trade) {
            this.trade = trade;
        }

        public String getPaytime() {
            return paytime;
        }

        public void setPaytime(String paytime) {
            this.paytime = paytime;
        }

        public String getPay_price() {
            return pay_price;
        }

        public void setPay_price(String pay_price) {
            this.pay_price = pay_price;
        }

        public String getPay_num() {
            return pay_num;
        }

        public void setPay_num(String pay_num) {
            this.pay_num = pay_num;
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

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public String getGimg() {
            return gimg;
        }

        public void setGimg(String gimg) {
            this.gimg = gimg;
        }

        public String getGteam_price() {
            return gteam_price;
        }

        public void setGteam_price(String gteam_price) {
            this.gteam_price = gteam_price;
        }

        public String getTrade_remark() {
            return trade_remark;
        }

        public void setTrade_remark(String trade_remark) {
            this.trade_remark = trade_remark;
        }
    }
}
