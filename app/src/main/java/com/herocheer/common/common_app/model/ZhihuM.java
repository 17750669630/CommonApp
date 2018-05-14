package com.herocheer.common.common_app.model;

import com.herocheer.common.common_app.contract.Contract;
import com.herocheer.common.common_app.contract.RetrofitService;
import com.herocheer.common.http.RetrofitFactory;
import com.herocheer.common.http.RxUtil;

import io.reactivex.Observable;

/**
 * @time:2018/4/9 at 10:51
 * @description: Model层
 */
public class ZhihuM implements Contract.ZhihuM {
    @Override
    public Observable<ZhiHuBean> getList() {
        return RetrofitFactory.createApi(RetrofitService.class)
                .test().compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Observable<ResultCode> getLIdentifyCode() {
        return RetrofitFactory.createApi(RetrofitService.class, RetrofitService.BASE_URL2)
                .getIdentifyCode().compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Observable<ResultCode> getLogin(String something) {
        return RetrofitFactory.createApi(RetrofitService.class, RetrofitService.BASE_URL2)
                .getLogin(something).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Observable<ResultCode> getRegister(String something) {
        return RetrofitFactory.createApi(RetrofitService.class, RetrofitService.BASE_URL2)
                .getRegister(something).compose(RxUtil.rxSchedulerHelper());
    }
}
