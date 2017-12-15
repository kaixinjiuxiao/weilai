package com.wlyilai.weilaibao.entry;

import java.util.List;

/**
 * @author: captain
 * Time:  2017/12/9 0009
 * Describe:
 */
public class Goods {

    /**
     * status : 1
     * data : [{"gname":"测试","gimg":"http://test.mgbh.wlylai.com./Uploads/GoodsSupply/2017-12-11/a7f233e8bb3435d71888.jpeg","gprice":"1.00","gteam_price":"2.00","gnum":"2","id":"1","guser_limit":"2","gstatus":"0"}]
     */

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * gname : 测试
         * gimg : http://test.mgbh.wlylai.com./Uploads/GoodsSupply/2017-12-11/a7f233e8bb3435d71888.jpeg
         * gprice : 1.00
         * gteam_price : 2.00
         * gnum : 2
         * id : 1
         * guser_limit : 2
         * gstatus : 0
         */

        private String gname;
        private String gimg;
        private String gprice;
        private String gteam_price;
        private String gnum;
        private String id;
        private String guser_limit;
        private String gstatus;

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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGuser_limit() {
            return guser_limit;
        }

        public void setGuser_limit(String guser_limit) {
            this.guser_limit = guser_limit;
        }

        public String getGstatus() {
            return gstatus;
        }

        public void setGstatus(String gstatus) {
            this.gstatus = gstatus;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "gname='" + gname + '\'' +
                    ", gimg='" + gimg + '\'' +
                    ", gprice='" + gprice + '\'' +
                    ", gteam_price='" + gteam_price + '\'' +
                    ", gnum='" + gnum + '\'' +
                    ", id='" + id + '\'' +
                    ", guser_limit='" + guser_limit + '\'' +
                    ", gstatus='" + gstatus + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Goods{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
