package com.herocheer.common.common_app.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.herocheer.common.common_app.contract.QulishiContract;
import com.herocheer.common.common_app.model.QulishiBean;
import com.herocheer.common.common_app.model.QulishiM;
import com.herocheer.common.common_app.model.QulishiPersonBean;
import com.herocheer.common.common_app.presenter.QulishiP;
import com.herocheer.common.common_mvp.mvp.BaseActivity;
import com.herocheer.common.commonapp.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

public class QulishiActivity extends BaseActivity<QulishiP, QulishiM> implements QulishiContract.QuView {

    @Override
    public int getLayoutId() {
        return R.layout.activity_qulishi;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        //mPresenter.requestRenwu();
        mPresenter.requestRenwuPerson("zhugeliang");
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showError(String msg) {
    }

    @Override
    public void returnRenwu(ArrayList<QulishiBean> beans) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ((RecyclerView) findViewById(R.id.rcvRenwu)).setLayoutManager(layoutManager);
        ((RecyclerView) findViewById(R.id.rcvRenwu)).setAdapter(new CommonAdapter<QulishiBean>
                (this, R.layout.item_renwu, beans) {
            @Override
            protected void convert(ViewHolder holder, QulishiBean bean, int position) {
                holder.setText(R.id.tvName, bean.getName());
            }
        });
    }

    @Override
    public void returnRenwuPerson(QulishiPersonBean bean) {
    }

    @Override
    public void returnRenwuPersonList(ArrayList<QulishiBean> beans) {

    }

    @Override
    public void returnRenwuWenzhang(String s) {

    }
}