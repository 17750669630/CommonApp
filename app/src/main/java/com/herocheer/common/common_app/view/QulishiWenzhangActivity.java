package com.herocheer.common.common_app.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.herocheer.common.common_app.contract.QulishiContract;
import com.herocheer.common.common_app.model.QulishiBean;
import com.herocheer.common.common_app.model.QulishiM;
import com.herocheer.common.common_app.model.QulishiPersonBean;
import com.herocheer.common.common_app.presenter.QulishiP;
import com.herocheer.common.common_mvp.mvp.BaseActivity;
import com.herocheer.common.commonapp.R;

import java.util.ArrayList;

public class QulishiWenzhangActivity extends BaseActivity<QulishiP, QulishiM> implements QulishiContract.QuView {

    @Override
    public int getLayoutId() {
        return R.layout.activity_qulishi_wenzhang;
    }

    Intent intent;
    Dialog dialog;
    @Override
    public void initView(Bundle savedInstanceState) {
        dialog = getProgress();
        dialog.show();
        intent = getIntent();
        mPresenter.requestRenwuWenzhang(intent.getStringExtra("key"));
    }

    @Override
    public void returnRenwu(ArrayList<QulishiBean> beans) {

    }

    @Override
    public void returnRenwuPerson(QulishiPersonBean bean) {

    }

    @Override
    public void returnRenwuPersonList(ArrayList<QulishiBean> beans) {

    }

    @Override
    public void returnRenwuWenzhang(String s) {
        dialog.dismiss();
        findViewById(R.id.ll_hind).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.tvWenzhang)).setText(s);
        ((TextView) findViewById(R.id.tvName)).setText(intent.getStringExtra("name"));
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void showError(String msg) {

    }

    Dialog getProgress() {
        Dialog dialog = new Dialog(this);
        //dialog.getWindow().setBackgroundDrawable(new BitmapDrawable());
        View dialogLayout = LayoutInflater.from(this.mContext).inflate(R.layout.dialog_wait_progressbar, null);
        dialog.setCancelable(false);// 不可以用"返回键"取消
        dialog.setContentView(dialogLayout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        return dialog;
    }
}
