package com.xuan.waitgamedemo.view;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName ArrayRecyclerAdapter
 * Description
 * Company
 * author  youxuan  E-mail:xuanyouwu@163.com
 * date createTime：2015/9/16 9:22
 * version
 */
public abstract class BaseArrayRecyclerAdapter<T> extends BaseRecyclerAdapter {
    public final List<T> dataList = new ArrayList<T>();

    public List<T> getData() {
        return dataList;
    }

    public T getData(int position) {
        if (position < 0 || position >= dataList.size()) return null;
        return dataList.get(position);
    }


    public boolean bindData(boolean isRefresh, List<T> datas) {
        if (datas == null) return false;
        if (isRefresh) dataList.clear();
        boolean b = dataList.addAll(datas);
        notifyDataSetChanged();
        return b;
    }

    public void clearData() {
        dataList.clear();
        notifyDataSetChanged();
    }

    @Override
    public T getItem(int position) {
        if (position < 0 || position >= dataList.size()) return null;
        return dataList.get(position);
    }

    public boolean addItem(int position, T t) {
        if (t == null) return false;
        if (position < 0 || position > dataList.size()) return false;
        if (dataList.contains(t)) return false;
        dataList.add(position, t);
        notifyItemInserted(position);
        return true;
    }

    public boolean addItems(int pos, List<? extends T> datas) {
        if (datas == null) return false;
        if (datas.contains(datas)) return false;
        dataList.addAll(pos, datas);
        notifyItemRangeInserted(pos, datas.size());
        return true;
    }

    public boolean addItems(List<? extends T> datas) {
        if (datas == null) return false;
        if (datas.contains(datas)) return false;
        dataList.addAll(datas);
        notifyItemRangeInserted(getItemCount() - datas.size() >= 0 ? getItemCount() - datas.size() : 0, datas.size());
        return true;
    }

    public boolean addItem(T t) {
        if (t == null) return false;
        if (dataList.contains(t)) return false;
        boolean b = dataList.add(t);
        notifyItemInserted(dataList.size() - 1);
        return b;
    }


    public boolean updateItem(int position) {
        if (position < 0 || position >= dataList.size()) return false;
        notifyItemChanged(position);
        return true;
    }

    public boolean updateItem(T t) {
        if (t == null) return false;
        int index = dataList.indexOf(t);
        if (index >= 0) {
            dataList.set(index, t);
            notifyItemChanged(index);
            return true;
        }
        return false;
    }

    public boolean updateItem(int position, T t) {
        if (position < 0 || position >= dataList.size()) return false;
        if (t == null) return false;
        dataList.set(position, t);
        notifyItemChanged(position);
        return true;
    }

    public boolean removeItem(int position) {
        if (position < 0 || position >= dataList.size()) return false;
        dataList.remove(position);
        notifyItemRemoved(position);
        return true;
    }

    public boolean removeItem(T t) {
        if (t == null) return false;
        int index = dataList.indexOf(t);
        if (index >= 0) {
            dataList.remove(index);
            notifyItemRemoved(index);
            return true;
        }
        return false;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        onBindHoder(holder, getData(position), position);
    }

    public abstract void onBindHoder(ViewHolder holder, T t, int position);

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}

