package wang.junqin.chaexpress.net;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import wang.junqin.chaexpress.model.bean.ExpressComBean;
import wang.junqin.chaexpress.model.bean.ExpressInfoBean;

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
