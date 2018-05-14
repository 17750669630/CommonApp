package com.herocheer.common.common_app.presenter;

import com.herocheer.common.common_app.contract.Contract;
import com.herocheer.common.common_mvp.tool.RxManager;

/**
 * @time:2018/4/9 at 11:11
 * @description:
 */
public class ZhihuP extends Contract.ZhihuP {

    @Override
    public void requestList() {
        RxManager.add(mModel.getList()
                .subscribe(mView::returnList,Throwable->mView.showError(Throwable.toString())));
    }

    @Override
    public void requestIdentifyCode() {
        RxManager.add(mModel.getLIdentifyCode()
                .subscribe(identifyCode -> mView.returnIdentifyCode(identifyCode),Throwable->mView.showError(Throwable.toString())));
    }

    @Override
    public void requestLLogin(String something) {
        RxManager.add(mModel.getLogin(something)
                .subscribe(resultCode -> mView.returnLogin(resultCode),Throwable->mView.showError(Throwable.toString())));
    }

    @Override
    public void requestRegister(String something) {
        RxManager.add(mModel.getRegister(something)
                .subscribe(resultCode -> mView.returnRegister(resultCode),Throwable->mView.showError(Throwable.toString())));
    }

    @Override
    public void onStart() {//预加载的时候进行的操作
        requestList();
    }
}
