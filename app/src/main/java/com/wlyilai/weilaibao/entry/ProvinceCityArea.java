package com.wlyilai.weilaibao.entry;

import java.util.List;

/**
 * @author: captain
 * Time:  2017/12/15 0015
 * Describe:
 */
public class ProvinceCityArea {

    /**
     * status : 1
     * data : [{"id":"7999","name":"请选择"},{"id":"8002","name":"北京"},{"id":"8022","name":"天津"},{"id":"8042","name":"河北"},{"id":"8226","name":"山西"},{"id":"8357","name":"内蒙古"},{"id":"8471","name":"辽宁"},{"id":"8586","name":"吉林"},{"id":"8656","name":"黑龙江"},{"id":"8800","name":"上海"},{"id":"8821","name":"江苏"},{"id":"8941","name":"浙江"},{"id":"9043","name":"安徽"},{"id":"9166","name":"福建"},{"id":"9261","name":"江西"},{"id":"9372","name":"山东"},{"id":"9530","name":"河南"},{"id":"9708","name":"湖北"},{"id":"9825","name":"湖南"},{"id":"9962","name":"广东"},{"id":"10114","name":"广西"},{"id":"10238","name":"海南"},{"id":"10264","name":"重庆"},{"id":"10306","name":"四川"},{"id":"10509","name":"贵州"},{"id":"10607","name":"云南"},{"id":"10753","name":"西藏"},{"id":"10834","name":"陕西"},{"id":"10952","name":"甘肃"},{"id":"11053","name":"青海"},{"id":"11105","name":"宁夏"},{"id":"11132","name":"新疆"},{"id":"11246","name":"香港"},{"id":"11248","name":"澳门"},{"id":"11250","name":"台湾"}]
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
         * id : 7999
         * name : 请选择
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
