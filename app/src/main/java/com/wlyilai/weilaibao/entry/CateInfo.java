package com.wlyilai.weilaibao.entry;

import java.util.List;

/**
 * @author: captain
 * Time:  2017/12/20 0020
 * Describe:
 */
public class CateInfo {

    /**
     * status : 1
     * data : [{"id":"141","name":"白酒"},{"id":"148","name":"其他饮品"},{"id":"147","name":"功能饮料"},{"id":"146","name":"牛奶饮品"},{"id":"145","name":"果汁饮品"},{"id":"144","name":"碳酸饮料"},{"id":"143","name":"药酒"},{"id":"142","name":"红酒"},{"id":"149","name":"咖啡"},{"id":"150","name":"茶叶"}]
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
         * id : 141
         * name : 白酒
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
