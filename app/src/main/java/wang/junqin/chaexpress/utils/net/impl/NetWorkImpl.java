package wang.junqin.chaexpress.utils.net.impl;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import wang.junqin.chaexpress.model.bean.ExpressComBean;
import wang.junqin.chaexpress.model.bean.ExpressInfoBean;
import wang.junqin.chaexpress.model.impl.Express;
import wang.junqin.chaexpress.utils.DAO.ExpressEntity;
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


    @Override
    public Observable<ExpressInfoBean> getExpressInfo(List<ExpressEntity> list){
        return Observable.fromIterable(list)
                .flatMap(new Function<ExpressEntity, ObservableSource<ExpressInfoBean>>() {
                    @Override
                    public ObservableSource<ExpressInfoBean> apply(@NonNull ExpressEntity entity) throws Exception {
                        return getExpressInfo(entity.getExpNum(),entity.getExpCom());
                    }
                });

    }


}
