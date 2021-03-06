package com.zhizhen.ybb.my.model;

import com.psylife.wrmvplibrary.data.net.RxService;
import com.psylife.wrmvplibrary.utils.helper.RxUtil;
import com.zhizhen.ybb.api.YbbApi;
import com.zhizhen.ybb.bean.BaseBean;
import com.zhizhen.ybb.my.contract.MyContract;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by tc on 2017/5/17.
 */

public class EditDataModelImp implements MyContract.EditDataModel {
    @Override
    public Observable<BaseBean> editPersonalInfo(MultipartBody photo) {
        return RxService.createApi(YbbApi.class).editPersonalInfo(photo).compose(RxUtil.rxSchedulerHelper());
    }
}
