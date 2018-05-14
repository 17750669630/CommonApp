package com.herocheer.common.common_app.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnRefreshListener;
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
import java.util.List;

import butterknife.BindView;

public class CharListActivity extends BaseActivity<QulishiP, QulishiM> implements QulishiContract.QuView,
        OnRefreshListener {
    @BindView(R.id.iRecyclerView)
    IRecyclerView iRecyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_char_list;
    }

    String key;
    private List<QulishiBean> list;
    CommonAdapter<QulishiBean> adapter;

    @Override
    public void initView(Bundle savedInstanceState) {
        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        mPresenter.requestRenwuCharList(key);
        list = new ArrayList<>();

        iRecyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_from_bottom));

        iRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        iRecyclerView.setOnRefreshListener(this);

        adapter = new CommonAdapter<QulishiBean>(this, R.layout.item_renwu, list) {
            @Override
            protected void convert(ViewHolder holder, QulishiBean bean, int position) {
                holder.setText(R.id.tvName, bean.getName());
                holder.setOnClickListener(R.id.tvName, v -> {
                    v.setBackground(getResources().getDrawable(R.drawable.a2zshape_light));
                    Intent i = new Intent(CharListActivity.this, QulishiPersonActivity.class);
                    i.putExtra("key", bean.getHref().substring(29));
                    startActivity(i);
                });
            }
        };
        iRecyclerView.setIAdapter(adapter);
    }

    @Override
    public void returnRenwu(ArrayList<QulishiBean> beans) {

    }

    @Override
    public void returnRenwuPerson(QulishiPersonBean bean) {

    }

    @Override
    public void returnRenwuPersonList(ArrayList<QulishiBean> beans) {
        iRecyclerView.setRefreshing(false);
        if (beans.size() > 0) {
            list.addAll(beans);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "该首字母没有内容哦", Toast.LENGTH_SHORT).show();
            this.finish();
        }

        List<String> list = new ArrayList<>();
        for (QulishiBean q : beans)
            list.add(q.getName());

        Dialog dialog = new Dialog(this, R.style.MyDialog);
        //dialog.getWindow().setBackgroundDrawable(new BitmapDrawable());
        RelativeLayout dialogLayout = (RelativeLayout) getLayoutInflater().inflate(R.layout.dialog_search, null);
        //dialog.setCancelable(false);// 不可以用"返回键"取消
        dialog.setContentView(dialogLayout);

        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);

        lp.x = 0; // 新位置X坐标
        lp.y = 50; // 新位置Y坐标
        //lp.width = 1000; // 宽度
        //lp.height = 0; // 高度
        lp.alpha = 0.9f; // 透明度

        dialogWindow.setAttributes(lp);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CharListActivity.this,
                android.R.layout.simple_list_item_1, list);
        ((AutoCompleteTextView) dialogLayout.findViewById(R.id.actSearch)).setAdapter(adapter);
        ((AutoCompleteTextView) dialogLayout.findViewById(R.id.actSearch)).setThreshold(1);
        ((AutoCompleteTextView) dialogLayout.findViewById(R.id.actSearch)).setOnItemClickListener((parent, view,
                                                                                                   position, id) -> {
            String href = beans.get(list.indexOf(((AutoCompleteTextView) dialogLayout.findViewById(R.id
                    .actSearch)).getText() + "")).getHref();
            ((AutoCompleteTextView) dialogLayout.findViewById(R.id.actSearch)).setText("");

            Intent intent = new Intent(CharListActivity.this, QulishiPersonActivity.class);
            intent.putExtra("key", href.substring(29));
            startActivity(intent);
            dialog.dismiss();
        });

        this.findViewById(R.id.fabSearch).setOnClickListener(v -> {
            dialog.show();
        });
    }

    @Override
    public void returnRenwuWenzhang(String s) {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onRefresh() {
        iRecyclerView.setRefreshing(true);
        list.clear();
        adapter.notifyDataSetChanged();
        mPresenter.requestRenwuCharList(key);
    }
}
