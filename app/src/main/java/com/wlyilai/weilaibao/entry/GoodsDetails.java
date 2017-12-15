package com.wlyilai.weilaibao.entry;

/**
 * @author: captain
 * Time:  2017/12/15 0015
 * Describe:
 */
public class GoodsDetails {

    /**
     * status : 1
     * data : {"gid":"10","gpay_num":"0","guser_limit":"2","gpay_limit":"2","sid":"20","gname":"测试","gprice":"1.00","gteam_price":"2.00","gnum":"2","gimg":"http://test.mgbh.wlylai.com./Uploads/GoodsSupply/2017-12-11/a7f233e8bb3435d71888.jpeg","sname":"体验店","gsn":"0001"}
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
         * gid : 10
         * gpay_num : 0
         * guser_limit : 2
         * gpay_limit : 2
         * sid : 20
         * gname : 测试
         * gprice : 1.00
         * gteam_price : 2.00
         * gnum : 2
         * gimg : http://test.mgbh.wlylai.com./Uploads/GoodsSupply/2017-12-11/a7f233e8bb3435d71888.jpeg
         * sname : 体验店
         * gsn : 0001
         */

        private String gid;
        private String gpay_num;
        private String guser_limit;
        private String gpay_limit;
        private String sid;
        private String gname;
        private String gprice;
        private String gteam_price;
        private String gnum;
        private String gimg;
        private String sname;
        private String gsn;

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getGpay_num() {
            return gpay_num;
        }

        public void setGpay_num(String gpay_num) {
            this.gpay_num = gpay_num;
        }

        public String getGuser_limit() {
            return guser_limit;
        }

        public void setGuser_limit(String guser_limit) {
            this.guser_limit = guser_limit;
        }

        public String getGpay_limit() {
            return gpay_limit;
        }

        public void setGpay_limit(String gpay_limit) {
            this.gpay_limit = gpay_limit;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public String getGprice() {
            return gprice;
        }

        public void setGprice(String gprice) {
            this.gprice = gprice;
        }

        public String getGteam_price() {
            return gteam_price;
        }

        public void setGteam_price(String gteam_price) {
            this.gteam_price = gteam_price;
        }

        public String getGnum() {
            return gnum;
        }

        public void setGnum(String gnum) {
            this.gnum = gnum;
        }

        public String getGimg() {
            return gimg;
        }

        public void setGimg(String gimg) {
            this.gimg = gimg;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public String getGsn() {
            return gsn;
        }

        public void setGsn(String gsn) {
            this.gsn = gsn;
        }
    }
}
