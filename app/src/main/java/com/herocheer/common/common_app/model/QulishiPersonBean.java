package com.herocheer.common.common_app.model;

import java.util.List;
import java.util.Objects;

/**
 * @time:2018/4/11 at 9:44
 * @description:
 */
public class QulishiPersonBean {
    String ziliao;
    String jieshao;
    String imgUrl;
    List<Wenzhang> wenzhangs;

    public QulishiPersonBean() {
    }

    public QulishiPersonBean(String ziliao, String jieshao, String imgUrl, List<Wenzhang> wenzhangs) {
        this.ziliao = ziliao;
        this.jieshao = jieshao;
        this.imgUrl = imgUrl;
        this.wenzhangs = wenzhangs;
    }

    public List<Wenzhang> getWenzhangs() {
        return wenzhangs;
    }

    public void setWenzhangs(List<Wenzhang> wenzhangs) {
        this.wenzhangs = wenzhangs;
    }

    public class Wenzhang {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Wenzhang wenzhang = (Wenzhang) o;
            return Objects.equals(wenzhangming, wenzhang.wenzhangming) &&
                    Objects.equals(wenzhangUrl, wenzhang.wenzhangUrl);
        }

        @Override
        public int hashCode() {
            return Objects.hash(wenzhangming,wenzhangUrl);
            /*int result = 17;
            result = 31 * result + wenzhangming.hashCode();
            result = 31 * result + wenzhangUrl.hashCode();
            return result;*/
        }

        String wenzhangming;
        String wenzhangUrl;

        public String getWenzhangming() {
            return wenzhangming;
        }

        public void setWenzhangming(String wenzhangming) {
            this.wenzhangming = wenzhangming;
        }

        public String getWenzhangUrl() {
            return wenzhangUrl;
        }

        public void setWenzhangUrl(String wenzhangUrl) {
            this.wenzhangUrl = wenzhangUrl;
        }

        public Wenzhang(String wenzhangming, String wenzhangUrl) {
            this.wenzhangming = wenzhangming;
            this.wenzhangUrl = wenzhangUrl;
        }
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getZiliao() {
        return ziliao;
    }

    public void setZiliao(String ziliao) {
        this.ziliao = ziliao;
    }

    public String getJieshao() {
        return jieshao;
    }

    public void setJieshao(String jieshao) {
        this.jieshao = jieshao;
    }

}
