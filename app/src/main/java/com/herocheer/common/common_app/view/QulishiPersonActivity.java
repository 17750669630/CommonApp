package com.herocheer.common.common_app.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.herocheer.common.common_app.contract.QulishiContract;
import com.herocheer.common.common_app.model.QulishiBean;
import com.herocheer.common.common_app.model.QulishiM;
import com.herocheer.common.common_app.model.QulishiPersonBean;
import com.herocheer.common.common_app.presenter.QulishiP;
import com.herocheer.common.common_app.tool.ScreenUtil;
import com.herocheer.common.common_mvp.mvp.BaseActivity;
import com.herocheer.common.commonapp.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

public class QulishiPersonActivity extends BaseActivity<QulishiP, QulishiM> implements QulishiContract.QuView {


    @Override
    public int getLayoutId() {
        return R.layout.activity_qulishi_person;
    }

    Dialog dialog;

    @Override
    public void initView(Bundle savedInstanceState) {
        dialog = getProgress();
        dialog.show();
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        mPresenter.requestRenwuPerson(key.substring(0, key.length() - 1));
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


    @Override
    public void returnRenwu(ArrayList<QulishiBean> beans) {

    }

    @Override
    public void returnRenwuPerson(QulishiPersonBean bean) {
        dialog.dismiss();
        findViewById(R.id.ll_person).setVisibility(View.VISIBLE);

        ((TextView) findViewById(R.id.tvZiliao)).setText(bean.getZiliao());
        ((TextView) findViewById(R.id.tvJieshao)).setText(bean.getJieshao());

        ImageView imageView = findViewById(R.id.imgRenwu);

        Glide.with(mContext).asBitmap().load(bean.getImgUrl())
                .apply(new RequestOptions().error(R.mipmap.icon_failure))
                .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                    //实现等比例设置宽高
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource
                            , @Nullable Transition<? super Bitmap> transition) {
                        int imageWidth = resource.getWidth();
                        int imageHeight = resource.getHeight();
                        int height = ScreenUtil.getScreenWidth(QulishiPersonActivity.this) * imageHeight / imageWidth;
                        ViewGroup.LayoutParams para = imageView.getLayoutParams();
                        para.height = height;
                        para.width = ScreenUtil.getScreenWidth(QulishiPersonActivity.this);
                        imageView.setImageBitmap(resource);
                    }
                });

        if (bean.getWenzhangs().size() > 0) {
            ((RecyclerView) findViewById(R.id.rcvwenzhang)).setLayoutAnimation(AnimationUtils.loadLayoutAnimation
                    (this, R.anim.layout_animation_from_bottom));
            ((RecyclerView) findViewById(R.id.rcvwenzhang)).setLayoutManager(new LinearLayoutManager(this));
            findViewById(R.id.rcvwenzhang).setNestedScrollingEnabled(false);//解决ScrollView嵌套RecyclerView导致滑动不流畅的问题
            ((RecyclerView) findViewById(R.id.rcvwenzhang)).setAdapter(new CommonAdapter<QulishiPersonBean.Wenzhang>
                    (this, R.layout.item_renwu, bean.getWenzhangs()) {
                @Override
                protected void convert(ViewHolder holder, QulishiPersonBean.Wenzhang bean, int position) {
                    holder.setText(R.id.tvName, bean.getWenzhangming());
                    holder.setOnClickListener(R.id.tvName, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.setBackground(getResources().getDrawable(R.drawable.a2zshape_light));
                            Intent i = new Intent(QulishiPersonActivity.this, QulishiWenzhangActivity.class);
                            i.putExtra("name", bean.getWenzhangming());
                            i.putExtra("key", bean.getWenzhangUrl().substring(23));
                            if (bean.getWenzhangUrl().contains("news"))//news需要补充两个空格
                                i.putExtra("flag", true);
                            startActivity(i);
                            //调到文章界面
                        }
                    });
                }
            });
        } else {
            Toast.makeText(this, "没有文章哦", Toast.LENGTH_SHORT).show();
        }

        for (QulishiPersonBean.Wenzhang wenzhang : bean.getWenzhangs()) {

        }
    }

    @Override
    public void returnRenwuPersonList(ArrayList<QulishiBean> beans) {

    }

    @Override
    public void returnRenwuWenzhang(String s) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showError(String msg) {

    }
}
