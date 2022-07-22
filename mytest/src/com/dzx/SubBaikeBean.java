package com.dzx;

import com.dzx.util.LUtils;

public class SubBaikeBean extends BaikeBean{

    private String tag = SubBaikeBean.class.getSimpleName();


    public void outTag1(){
        LUtils.i("子类tag  =  "+tag);
    }


}
