package com.herocheer.common.http;

import com.herocheer.common.common_app.AppContext;

import java.io.File;

import okhttp3.Cache;

/**
 * @time:2018/4/9 at 10:08
 * @description: Http缓存
 */

public class HttpCache {

    private static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 50 * 1024 * 1024;

    public static Cache getCache() {
        return new Cache(new File(AppContext.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data/NetCache"),
                HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);
    }
}
