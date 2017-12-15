package com.wlyilai.weilaibao.entry;

import java.util.List;

/**
 * Author : Captain
 * Time : 2017/12/4
 * Describe :
 */

public class ReceivingAddress {

    /**
     * status : 1
     * data : {"address":[{"id":"2","uid":"198","mobile":"15000000000","realname":"李四","province":"河南","city":"郑州市","country":"中原区","detail":"哈哈"},{"id":"1","uid":"198","mobile":"15000000000","realname":"张三","province":"湖南","city":"长沙市","country":"雨花区","detail":"万家丽街道华晨双帆国际"}]}
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
        private List<AddressBean> address;

        public List<AddressBean> getAddress() {
            return address;
        }

        public void setAddress(List<AddressBean> address) {
            this.address = address;
        }

        public static class AddressBean {
            /**
             * id : 2
             * uid : 198
             * mobile : 15000000000
             * realname : 李四
             * province : 河南
             * city : 郑州市
             * country : 中原区
             * detail : 哈哈
             */

            private String id;
            private String uid;
            private String mobile;
            private String realname;
            private String province;
            private String city;
            private String country;
            private String detail;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
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

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            @Override
            public String toString() {
                return "AddressBean{" +
                        "id='" + id + '\'' +
                        ", uid='" + uid + '\'' +
                        ", mobile='" + mobile + '\'' +
                        ", realname='" + realname + '\'' +
                        ", province='" + province + '\'' +
                        ", city='" + city + '\'' +
                        ", country='" + country + '\'' +
                        ", detail='" + detail + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "address=" + address +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ReceivingAddress{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }
}
