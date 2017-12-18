package com.wlyilai.weilaibao.entry;

import java.util.List;

/**
 * @author: captain
 * Time:  2017/12/12 0012
 * Describe:
 */
public class MyGroup {

    /**
     * status : 1
     * data : [{"osn":"62017121816012423680","total_price":"0.20","pay_num":"2","grid":"11","gname":"商品-4","gteam_price":"0.10","gimg":"http://test.mgbh.wlylai.com./Uploads/GoodsSupply/2017-12-15/df52a97c3afd6b918960.jpeg"},{"osn":"62017121816010787604","total_price":"0.40","pay_num":"4","grid":"11","gname":"商品-4","gteam_price":"0.10","gimg":"http://test.mgbh.wlylai.com./Uploads/GoodsSupply/2017-12-15/df52a97c3afd6b918960.jpeg"},{"osn":"62017121815561848526","total_price":"0.20","pay_num":"2","grid":"11","gname":"商品-4","gteam_price":"0.10","gimg":"http://test.mgbh.wlylai.com./Uploads/GoodsSupply/2017-12-15/df52a97c3afd6b918960.jpeg"}]
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
         * osn : 62017121816012423680
         * total_price : 0.20
         * pay_num : 2
         * grid : 11
         * gname : 商品-4
         * gteam_price : 0.10
         * gimg : http://test.mgbh.wlylai.com./Uploads/GoodsSupply/2017-12-15/df52a97c3afd6b918960.jpeg
         */

        private String osn;
        private String total_price;
        private String pay_num;
        private String grid;
        private String gname;
        private String gteam_price;
        private String gimg;
        private String state;

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getOsn() {
            return osn;
        }

        public void setOsn(String osn) {
            this.osn = osn;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public String getPay_num() {
            return pay_num;
        }

        public void setPay_num(String pay_num) {
            this.pay_num = pay_num;
        }

        public String getGrid() {
            return grid;
        }

        public void setGrid(String grid) {
            this.grid = grid;
        }

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
    }
}
