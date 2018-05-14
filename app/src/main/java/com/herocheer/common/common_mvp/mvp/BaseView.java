package com.herocheer.common.common_mvp.mvp;

import android.content.Context;

/**
 * @time:2018/4/9 at 10:22
 * @description: BaseView
 */
public interface BaseView {
    Context getContext();
    void showError(String msg);
}
