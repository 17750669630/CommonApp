package com.herocheer.common.common_app.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;

import com.herocheer.common.commonapp.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class A2ZActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a2_z);

        List<String> a2z = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            a2z.add(c + "");
        }
        ((RecyclerView) findViewById(R.id.rcvA2Z)).setLayoutAnimation(AnimationUtils.loadLayoutAnimation
                (this, R.anim.grid_layout_animation_from_bottom));
        ((RecyclerView) findViewById(R.id.rcvA2Z)).setLayoutManager(new GridLayoutManager(this, 4));
        ((RecyclerView) findViewById(R.id.rcvA2Z)).setAdapter(new CommonAdapter<String>
                (this, R.layout.item_a2z, a2z) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tvC, a2z.get(position));
                holder.setOnClickListener(R.id.rlC, v -> {
                    v.setBackground(getResources().getDrawable(R.drawable.a2zshape_light));
                    Intent i = new Intent(A2ZActivity.this, CharListActivity.class);
                    i.putExtra("key", "list_" + a2z.get(position).toLowerCase() + ".htm");
                    startActivity(i);
                });
            }
        });
    }
}
