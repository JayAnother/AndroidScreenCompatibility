package com.jay.androidscreencompatibility.ui.main;

import com.jay.androidscreencompatibility.R;
import com.jay.androidscreencompatibility.ui.layout.ConstraintLayoutActivity;
import com.jay.androidscreencompatibility.ui.layout.LayoutDirectionActivity;
import com.jay.androidscreencompatibility.ui.splash.SplashActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DemoActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(getLinearLayoutManager());
        recyclerview.setAdapter(getDemoAdapter());
    }
    private List<DemoAdapter.Demo> getDemoData() {
        List<DemoAdapter.Demo> demoList = new ArrayList<>();
        demoList.add(new DemoAdapter.Demo("SplashActivity", "SplashActivity",
            SplashActivity.class));
        demoList.add(new DemoAdapter.Demo("LayoutDirectionActivity", "LayoutDirectionActivity",
            LayoutDirectionActivity.class));
        demoList.add(new DemoAdapter.Demo("ConstraintLayoutActivity", "ConstraintLayoutActivity",
            ConstraintLayoutActivity.class));

        return demoList;
    }


    private DemoAdapter getDemoAdapter() {
        return new DemoAdapter(getDemoData(), new DemoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DemoAdapter.Demo item) {
                startActivity(new Intent(DemoActivity.this, item.getActivity()));
            }
        });
    }

    private LinearLayoutManager getLinearLayoutManager() {
        return new LinearLayoutManager(this);
    }
}
