package com.wlyilai.weilaibao.entry;

import java.io.Serializable;

/**
 * @author: captain
 * Time:  2017/12/18 0018
 * Describe:
 */
public class PayResult implements Serializable{

    /**
     * status : 1
     * data : {"uid":"198","out_trade_type":"2","pay_price":"0.20","id":"13","pay_num":"2","grid":"11"}
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
         * uid : 198
         * out_trade_type : 2
         * pay_price : 0.20
         * id : 13
         * pay_num : 2
         * grid : 11
         */

        private String uid;
        private String out_trade_type;
        private String pay_price;
        private String id;
        private String pay_num;
        private String grid;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getOut_trade_type() {
            return out_trade_type;
        }

        public void setOut_trade_type(String out_trade_type) {
            this.out_trade_type = out_trade_type;
        }

        public String getPay_price() {
            return pay_price;
        }

        public void setPay_price(String pay_price) {
            this.pay_price = pay_price;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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
    }
}
