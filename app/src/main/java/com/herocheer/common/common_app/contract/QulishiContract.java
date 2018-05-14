package com.herocheer.common.common_app.contract;

import com.herocheer.common.common_app.model.QulishiBean;
import com.herocheer.common.common_app.model.QulishiPersonBean;
import com.herocheer.common.common_mvp.mvp.BaseModel;
import com.herocheer.common.common_mvp.mvp.BasePresenter;
import com.herocheer.common.common_mvp.mvp.BaseView;

import java.util.ArrayList;

import io.reactivex.Observable;
import okhttp3.ResponseBody;


/**
 * @time:2018/4/10 at 14:29
 * @description:
 * 1.获取首页上的人物
 * 2.获取某个人物信息
 * 3.获取某字母开头的人物列表
 * 4.获取文章信息
 */
public interface QulishiContract {
    interface QuModel extends BaseModel {
        Observable<ResponseBody> getRenwu();

        Observable<ResponseBody> getRenwuPerson(String person);

        Observable<ResponseBody> getRenwuCharList(String s);

        Observable<ResponseBody> getRenwuWenzhang(String wenzhang);
    }

    interface QuView extends BaseView {
        void returnRenwu(ArrayList<QulishiBean> beans);

        void returnRenwuPerson(QulishiPersonBean bean);

        void returnRenwuPersonList(ArrayList<QulishiBean> beans);

        void returnRenwuWenzhang(String s);
    }

    abstract class QuPresenter extends BasePresenter<QuModel, QuView> {
        public abstract void requestRenwu();

        public abstract void requestRenwuPerson(String s);

        public abstract void requestRenwuCharList(String s);

        public abstract void requestRenwuWenzhang(String s);
    }
}
