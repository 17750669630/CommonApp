package com.herocheer.common.common_app.model;

import com.herocheer.common.common_app.contract.QulishiContract;
import com.herocheer.common.common_app.contract.RetrofitService;
import com.herocheer.common.http.RetrofitFactory;
import com.herocheer.common.http.RxUtil;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @time:2018/4/10 at 14:35
 * @description:
 */
public class QulishiM implements QulishiContract.QuModel {
    @Override
    public Observable<ResponseBody> getRenwu() {
        return RetrofitFactory.createApi(RetrofitService.class, RetrofitService.BASE_URL3)
                .getRenwu().compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Observable<ResponseBody> getRenwuPerson(String person) {
        return RetrofitFactory.createApi(RetrofitService.class, RetrofitService.BASE_URL3)
                .getRenwuOne(person).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Observable<ResponseBody> getRenwuCharList(String s) {
        return RetrofitFactory.createApi(RetrofitService.class, RetrofitService.BASE_URL3)
                .getRenwuChar(s).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Observable<ResponseBody> getRenwuWenzhang(String wenzhang) {
        return RetrofitFactory.createApi(RetrofitService.class, RetrofitService.BASE_URL3)
                .getRenwuWenzhang(wenzhang).compose(RxUtil.rxSchedulerHelper());
    }
}
