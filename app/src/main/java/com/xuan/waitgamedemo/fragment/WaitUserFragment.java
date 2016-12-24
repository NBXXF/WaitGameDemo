package com.xuan.waitgamedemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xuan.waitgamedemo.R;
import com.xuan.waitgamedemo.adapter.UserAdapter;
import com.xuan.waitgamedemo.interf.OnFragmentCallBackListener;
import com.xuan.waitgamedemo.model.User;
import com.xuan.waitgamedemo.utils.SnackbarUtils;
import com.xuan.waitgamedemo.view.BaseRecyclerAdapter;

import java.util.List;

/**
 * Created by axuan on 2016/12/24.
 */

public class WaitUserFragment extends LazyBaseFragment implements BaseRecyclerAdapter.OnItemClickListener {

    private static final String KEY_INDEX = "key_index";
    public static final String KEY_USERS = "key_users";

    public static WaitUserFragment newInstance(int index) {
        WaitUserFragment waitUserFragment = new WaitUserFragment();
        Bundle buddle = new Bundle();
        buddle.putInt(KEY_INDEX, index);
        waitUserFragment.setArguments(buddle);
        return waitUserFragment;
    }

    View rootView;
    private RecyclerView recyclerView;
    UserAdapter userAdapter;
    private OnFragmentCallBackListener onFragmentCallBackListener;
    private Handler handler = new Handler();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.onFragmentCallBackListener = (OnFragmentCallBackListener) context;
        } catch (ClassCastException e) {
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_wait_user, container, false);
            initView();
        }
        if (rootView.getParent() != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }
        return rootView;
    }

    private void initView() {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(userAdapter = new UserAdapter());
        userAdapter.setOnItemClickListener(this);
    }

    private void getData() {
        //从网络获取数据 模拟延时
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<User> users = User.generateRandomUsers();
                userAdapter.bindData(true, users);
                if (onFragmentCallBackListener != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(KEY_USERS, userAdapter.getItemCount());
                    onFragmentCallBackListener.OnFragmentCallBack(WaitUserFragment.this, bundle);
                }
            }
        }, 1_000);
    }

    @Override
    public void onLazyLoad() {
        getData();
    }

    @Override
    public void onItemClick(BaseRecyclerAdapter adapter, BaseRecyclerAdapter.ViewHolder holder, View view, int position) {
        SnackbarUtils.showSnack(getActivity(), "点击了:" + position);
    }
}
