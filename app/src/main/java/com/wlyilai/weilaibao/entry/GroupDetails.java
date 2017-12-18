package com.wlyilai.weilaibao.entry;

import java.util.List;

/**
 * @author: captain
 * Time:  2017/12/18 0018
 * Describe:
 */
public class GroupDetails {

    /**
     * status : 1
     * data : {"gnum":"10","gpay_num":"10","order_detail":[{"mobile":"155****3896","paytime":"2017-12-18 16:32:20"},{"mobile":"155****3896","paytime":"2017-12-18 16:01:26"},{"mobile":"155****3896","paytime":"2017-12-18 16:01:09"},{"mobile":"155****3896","paytime":"2017-12-18 15:56:21"},{"mobile":"155****3896","paytime":"0000-00-00 00:00:00"},{"mobile":"155****3896","paytime":"0000-00-00 00:00:00"},{"mobile":"155****3896","paytime":"0000-00-00 00:00:00"},{"mobile":"155****3896","paytime":"0000-00-00 00:00:00"},{"mobile":"155****3896","paytime":"0000-00-00 00:00:00"},{"mobile":"155****3896","paytime":"0000-00-00 00:00:00"},{"mobile":"155****3896","paytime":"0000-00-00 00:00:00"},{"mobile":"155****3896","paytime":"0000-00-00 00:00:00"},{"mobile":"155****3896","paytime":"0000-00-00 00:00:00"}]}
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
         * gnum : 10
         * gpay_num : 10
         * order_detail : [{"mobile":"155****3896","paytime":"2017-12-18 16:32:20"},{"mobile":"155****3896","paytime":"2017-12-18 16:01:26"},{"mobile":"155****3896","paytime":"2017-12-18 16:01:09"},{"mobile":"155****3896","paytime":"2017-12-18 15:56:21"},{"mobile":"155****3896","paytime":"0000-00-00 00:00:00"},{"mobile":"155****3896","paytime":"0000-00-00 00:00:00"},{"mobile":"155****3896","paytime":"0000-00-00 00:00:00"},{"mobile":"155****3896","paytime":"0000-00-00 00:00:00"},{"mobile":"155****3896","paytime":"0000-00-00 00:00:00"},{"mobile":"155****3896","paytime":"0000-00-00 00:00:00"},{"mobile":"155****3896","paytime":"0000-00-00 00:00:00"},{"mobile":"155****3896","paytime":"0000-00-00 00:00:00"},{"mobile":"155****3896","paytime":"0000-00-00 00:00:00"}]
         */

        private String gnum;
        private String gpay_num;
        private List<OrderDetailBean> order_detail;

        public String getGnum() {
            return gnum;
        }

        public void setGnum(String gnum) {
            this.gnum = gnum;
        }

        public String getGpay_num() {
            return gpay_num;
        }

        public void setGpay_num(String gpay_num) {
            this.gpay_num = gpay_num;
        }

        public List<OrderDetailBean> getOrder_detail() {
            return order_detail;
        }

        public void setOrder_detail(List<OrderDetailBean> order_detail) {
            this.order_detail = order_detail;
        }

        public static class OrderDetailBean {
            /**
             * mobile : 155****3896
             * paytime : 2017-12-18 16:32:20
             */

            private String mobile;
            private String paytime;

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getPaytime() {
                return paytime;
            }

            public void setPaytime(String paytime) {
                this.paytime = paytime;
            }
        }
    }
}
