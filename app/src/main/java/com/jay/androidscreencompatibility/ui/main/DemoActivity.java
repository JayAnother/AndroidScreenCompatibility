package com.jay.androidscreencompatibility.ui.main;

import com.jay.androidscreencompatibility.R;
import com.jay.androidscreencompatibility.ui.adaptation_bar.DisplayCutoutActivity;
import com.jay.androidscreencompatibility.ui.adaptation_density.DensityAdaptActivity;
import com.jay.androidscreencompatibility.ui.adaptation_dp.DPAdaptActivity;
import com.jay.androidscreencompatibility.ui.adaptation_sw.SWAdaptActivity;
import com.jay.androidscreencompatibility.ui.adaptation_view.ViewAdaptActivity;
import com.jay.androidscreencompatibility.ui.others.ConstraintLayoutActivity;
import com.jay.androidscreencompatibility.ui.others.LayoutDirectionActivity;
import com.jay.androidscreencompatibility.ui.others.ScreenInfoActivityPlus;
import com.jay.androidscreencompatibility.ui.others.ScreenInfohActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DemoActivity extends BaseActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(getLinearLayoutManager());
        recyclerview.setAdapter(getDemoAdapter());
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    private List<DemoAdapter.Demo> getDemoData() {
        List<DemoAdapter.Demo> demoList = new ArrayList<>();
        demoList.add(new DemoAdapter.Demo("ScreenInfohActivity", "设备信息",
            ScreenInfohActivity.class));
        demoList.add(new DemoAdapter.Demo("ScreenInfoActivityPlus", "设备信息",
            ScreenInfoActivityPlus.class));
        demoList.add(new DemoAdapter.Demo("LayoutDirectionActivity", "布局方向（LTR or RTL）",
            LayoutDirectionActivity.class));
        demoList.add(new DemoAdapter.Demo("ConstraintLayoutActivity", "Android UI神兵利器-约束布局",
            ConstraintLayoutActivity.class));
        demoList.add(new DemoAdapter.Demo("DPAdaptActivity", "DP直接适配",
            DPAdaptActivity.class));
        demoList.add(new DemoAdapter.Demo("SWAdaptActivity", "最小宽度限定符适配方案",
            SWAdaptActivity.class));
        demoList.add(new DemoAdapter.Demo("DensityAdaptActivity", "今日头条适配方案(修改系统Density)",
            DensityAdaptActivity.class));
//        demoList.add(new DemoAdapter.Demo("ViewAdaptActivity", "自定义View适配方案",
//            ViewAdaptActivity.class));
        demoList.add(new DemoAdapter.Demo("DisplayCutoutActivity", "刘海屏幕适配",
            DisplayCutoutActivity.class));

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
