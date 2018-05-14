package com.herocheer.common.common_app.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.herocheer.common.common_app.contract.Contract.ZhihuV;
import com.herocheer.common.common_app.model.ResultCode;
import com.herocheer.common.common_app.model.ZhiHuBean;
import com.herocheer.common.common_app.model.ZhihuM;
import com.herocheer.common.common_app.presenter.ZhihuP;
import com.herocheer.common.common_mvp.mvp.BaseActivity;
import com.herocheer.common.common_mvp.tool.LogUtil;
import com.herocheer.common.common_mvp.tool.StatusBarUtil;
import com.herocheer.common.commonapp.R;

/**
 * @time:2018/4/9 at 9:40
 * @description: MainActivity
 */

public class MainActivity extends BaseActivity<ZhihuP, ZhihuM> implements ZhihuV {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        StatusBarUtil.setColor(this, Color.LTGRAY);

        findViewById(R.id.button2).setOnClickListener(v -> mPresenter.requestIdentifyCode());
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //测试RxBus
                //RxBus.$().post("t","s");

                mPresenter.requestLLogin("");
                mPresenter.requestLLogin("1");
                mPresenter.requestRegister("");
                mPresenter.requestRegister("1");
            }
        });


        //测试RxBus
        /*RxBus.$().OnEvent(RxBus.$().register("t"), new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                LogUtil.e((String)o);
            }
        });*/
    }

    @Override
    public void returnList(ZhiHuBean s) {
        for (ZhiHuBean.StoriesBean b : s.getStories())
            LogUtil.e(b.getTitle());
    }

    @Override
    public void returnIdentifyCode(ResultCode s) {
        LogUtil.e("identifyCode"+s.getCode());
    }

    @Override
    public void returnLogin(ResultCode s) {
        LogUtil.e("login"+s.getCode());
    }

    @Override
    public void returnRegister(ResultCode s) {
        LogUtil.e("register"+s.getCode());
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showError(String msg) {
        LogUtil.e("error"+msg);
    }

}
