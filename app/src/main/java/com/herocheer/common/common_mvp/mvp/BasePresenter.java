package com.herocheer.common.common_mvp.mvp;

import com.herocheer.common.common_mvp.tool.RxManager;

/**
 * @time:2018/4/9 at 10:24
 * @description:
 */
public abstract class BasePresenter<M, T> {
    public M mModel;
    public T mView;
    public RxManager mRxManager = new RxManager();

    public void attachVM(T v, M m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public void detachVM() {
        mRxManager.clear();
        mView = null;
        mModel = null;
    }

    public abstract void onStart();
}
