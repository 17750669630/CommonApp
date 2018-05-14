package com.herocheer.common.common_app.contract;

import com.herocheer.common.common_app.model.ResultCode;
import com.herocheer.common.common_app.model.ZhiHuBean;
import com.herocheer.common.common_mvp.mvp.BaseModel;
import com.herocheer.common.common_mvp.mvp.BasePresenter;
import com.herocheer.common.common_mvp.mvp.BaseView;

import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;

/**
 * @time:2018/4/9 at 10:22
 * @description: 协议vmp
 */
public interface Contract {

    interface ZhihuM extends BaseModel {
        Observable<ZhiHuBean> getList();

        Observable<ResultCode> getLIdentifyCode();

        Observable<ResultCode> getLogin(String something);

        Observable<ResultCode> getRegister(String something);

    }

    interface ZhihuV extends BaseView {
        void returnList(ZhiHuBean s);

        void returnIdentifyCode(ResultCode s);

        void returnLogin(ResultCode s);

        void returnRegister(ResultCode s);
    }

    abstract class ZhihuP extends BasePresenter<ZhihuM, ZhihuV> {
        public abstract void requestList();

        public abstract void requestIdentifyCode() throws TimeoutException;

        public abstract void requestLLogin(String something) throws TimeoutException;

        public abstract void requestRegister(String something) throws TimeoutException;
    }
}
