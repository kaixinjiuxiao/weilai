package com.wlyilai.weilaibao.entry;

import java.util.List;

/**
 * @author: captain
 * Time:  2017/12/15 0015
 * Describe:
 */
public class TypeAndBanner {
    /**
     * status : 1
     * data : {"cate":[{"id":"140","name":"酒水饮料"},{"id":"137","name":"商超综合"},{"id":"86","name":"缴费付费"},{"id":"81","name":"汽车住房"},{"id":"77","name":"日用百货"},{"id":"72","name":"鞋帽配件"},{"id":"63","name":"钟表首饰"},{"id":"127","name":"旅游住宿"},{"id":"116","name":"餐饮茶点"},{"id":"59","name":"图书乐器"},{"id":"15","name":"休闲健身"},{"id":"16","name":"家居家电"},{"id":"29","name":"美妆个户"},{"id":"30","name":"食品生鲜"},{"id":"39","name":"医药保健"},{"id":"46","name":"数码办公"},{"id":"4","name":"母婴玩具"},{"id":"139","name":"其他"}],"banner":[{"img":"./Uploads/banner/2017-10-28/59f4268d787b7.jpg","url":""},{"img":"./Uploads/banner/2017-10-30/59f6ca5c709e8.jpg","url":""},{"img":"./Uploads/banner/2017-11-01/59f9724211934.jpg","url":""},{"img":"./Uploads/banner/2017-09-02/59aa4a0c306b8.jpg","url":""},{"img":"./Uploads/banner/2017-10-09/59db20555aae9.jpg","url":""},{"img":"./Uploads/banner/2017-10-10/59dc6bd370c39.jpg","url":""},{"img":"./Uploads/banner/2017-10-18/59e70bfd43acc.jpg","url":""}]}
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
        private List<CateBean> cate;
        private List<BannerBean> banner;

        public List<CateBean> getCate() {
            return cate;
        }

        public void setCate(List<CateBean> cate) {
            this.cate = cate;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public static class CateBean {
            /**
             * id : 140
             * name : 酒水饮料
             */

            private String id;
            private String name;

            public CateBean(String id, String name) {
                this.id = id;
                this.name = name;
            }

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

        public static class BannerBean {
            /**
             * img : ./Uploads/banner/2017-10-28/59f4268d787b7.jpg
             * url :
             */

            private String img;
            private String url;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
