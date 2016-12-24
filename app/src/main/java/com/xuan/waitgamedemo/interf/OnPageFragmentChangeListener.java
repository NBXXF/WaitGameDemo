package com.xuan.waitgamedemo.interf;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Description
 * Company
 * author  youxuan  E-mail:xuanyouwu@163.com
 * date createTime：16/12/22
 * version
 */

public interface OnPageFragmentChangeListener {

    void onPageFragmentSelected(Fragment frgament, int pos, Bundle bundle);
}