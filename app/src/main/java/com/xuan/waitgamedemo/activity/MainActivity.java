package com.xuan.waitgamedemo.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseIntArray;

import com.xuan.waitgamedemo.BaseFragmentAdapter;
import com.xuan.waitgamedemo.interf.OnFragmentCallBackListener;
import com.xuan.waitgamedemo.R;
import com.xuan.waitgamedemo.fragment.WaitUserFragment;
import com.xuan.waitgamedemo.interf.OnPageFragmentChangeListener;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements OnFragmentCallBackListener {
    BaseFragmentAdapter fragmentAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;
    private final SparseIntArray vouchersNumArray = new SparseIntArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        fragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                for (int i = 0; i < fragmentAdapter.getFragmentsList().size(); i++) {
                    Fragment fragment = fragmentAdapter.getItem(position);
                    fragment.setUserVisibleHint(i == position);
                    if (fragment instanceof OnPageFragmentChangeListener) {
                        ((OnPageFragmentChangeListener) fragment).onPageFragmentSelected(fragment, position, null);
                    }
                }
            }
        });
        getTitleMenu();
    }

    private void getTitleMenu() {
        //模拟 获取到三个菜单
        fragmentAdapter.bindTitle(true, Arrays.asList("2-4", "5-8", "9-12"));
        fragmentAdapter.bindData(true,
                Arrays.asList(
                        WaitUserFragment.newInstance(0).startLazyMode(),
                        WaitUserFragment.newInstance(1),
                        WaitUserFragment.newInstance(2)));
    }

    @Override
    public void OnFragmentCallBack(Fragment fragment, Bundle params) {
        int index = fragmentAdapter.getFragmentsList().indexOf(fragment);
        vouchersNumArray.put(index, params.getInt(WaitUserFragment.KEY_USERS));
        //动态修改tabLayout 指示器
        //方式1：
        // tabLayout.getTabAt(0).setText("未使用(%s)");

        //方式2:
        fragmentAdapter.bindTitle(true,
                Arrays.asList(
                        String.format("2-4(%s)", vouchersNumArray.get(0)),
                        String.format("5-8(%s)", vouchersNumArray.get(1)),
                        String.format("9-12(%s)", vouchersNumArray.get(2))));
    }
}
