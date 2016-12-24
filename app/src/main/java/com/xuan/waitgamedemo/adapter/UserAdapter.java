package com.xuan.waitgamedemo.adapter;

import android.widget.TextView;

import com.xuan.waitgamedemo.R;
import com.xuan.waitgamedemo.model.User;
import com.xuan.waitgamedemo.view.BaseArrayRecyclerAdapter;
import com.xuan.waitgamedemo.view.BaseRecyclerAdapter;

/**
 * Created by axuan on 2016/12/24.
 */

public class UserAdapter extends BaseArrayRecyclerAdapter<User> {
    @Override
    public void onBindHoder(ViewHolder holder, User user, int position) {
        if (user == null) return;
        TextView tvName = holder.obtainView(R.id.tv_name);
        TextView tvAge = holder.obtainView(R.id.tv_age);
        tvName.setText(user.getName());
        tvAge.setText(user.getAge());
    }

    @Override
    public int bindView(int viewtype) {
        return R.layout.item_user;
    }
}
