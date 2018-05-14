package com.herocheer.common.common_app.model;

/**
 * @time:2018/4/10 at 16:59
 * @description:
 */
public class QulishiBean {
    String name;
    String href;

    public QulishiBean(String name, String href) {
        this.name = name;
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
