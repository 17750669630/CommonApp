package com.herocheer.common.common_app;

import android.app.Application;

/**
 * @time:2018/4/9 at 10:00
 * @description: 单例模式 在没有activity和context时候获取application对象
 */
public class AppContext extends Application {

    private static AppContext instance;

    public static AppContext getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
