package com.xuan.waitgamedemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName
 * Description
 * Company
 * author  youxuan  E-mail:xuanyouwu@163.com
 * date createTime：2015/8/13 10:26
 * version
 */
public class BaseFragmentAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentsList = new ArrayList<Fragment>();
    private final List<CharSequence> mFragmentTitles = new ArrayList<CharSequence>();
    private FragmentManager fm;

    public List<Fragment> getFragmentsList() {
        return fragmentsList;
    }


    public BaseFragmentAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    public void bindData(boolean isRefresh, List<? extends Fragment> datas) {
        if (datas == null) return;
        if (isRefresh) this.fragmentsList.clear();
        this.fragmentsList.addAll(datas);
        notifyDataSetChanged();
    }

    public void bindTitle(boolean isRefresh, List<? extends CharSequence> titles) {
        if (titles == null) return;
        if (isRefresh) mFragmentTitles.clear();
        mFragmentTitles.addAll(titles);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return fragmentsList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object obj = super.instantiateItem(container, position);
        if (obj instanceof Fragment) {
            try {
                fragmentsList.set(position, (Fragment) obj);
            } catch (Exception e) {
            }
        }
        return obj;
    }

    @Override
    public Fragment getItem(int arg0) {
        return fragmentsList.get(arg0);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mFragmentTitles.isEmpty()) return "";
        return mFragmentTitles.get(position % mFragmentTitles.size());
    }
}

