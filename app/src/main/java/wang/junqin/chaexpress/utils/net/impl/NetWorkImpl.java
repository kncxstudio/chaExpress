package wang.junqin.chaexpress.utils.net.impl;

import java.util.List;

import io.reactivex.Observable;
import wang.junqin.chaexpress.model.bean.ExpressComBean;
import wang.junqin.chaexpress.model.bean.ExpressInfoBean;
import wang.junqin.chaexpress.utils.net.NetWork;

/**
 * Created by KN on 2017/5/29.
 */

public enum NetWorkImpl implements NetWork {

    INSTANCE;



    String TAG = this.getClass().getName();


    @Override
    public Observable<List<ExpressComBean>> getExpressCom(String expressNum) {
        return RetrofitEntity.create().getExpressCom(expressNum);
    }

    @Override
    public Observable<ExpressInfoBean> getExpressInfo(String expNum, String comNum) {
        return RetrofitEntity.create().getExpressInfo(expNum,comNum);
    }
}
