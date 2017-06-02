package wang.junqin.chaexpress.model.impl;

import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.objectbox.Box;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import wang.junqin.chaexpress.DAO.DAOUtils;
import wang.junqin.chaexpress.DAO.ExpressEntity;
import wang.junqin.chaexpress.model.IExpress;
import wang.junqin.chaexpress.model.bean.ExpressComBean;
import wang.junqin.chaexpress.model.bean.ExpressInfoBean;
import wang.junqin.chaexpress.net.NetWork;
import wang.junqin.chaexpress.net.impl.NetWorkImpl;
import wang.junqin.chaexpress.presenter.QueryListener;

/**
 * Created by KN on 2017/5/29.
 */

public class Express implements IExpress {
    String TAG = this.getClass().getName();
    NetWork netWork = NetWorkImpl.INSTANCE;

    @Override
    public void queryExpCom(final String expNum, final QueryListener listener) {
        netWork.getExpressCom(expNum)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<ExpressComBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e(TAG,"onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull List<ExpressComBean> expressComBeans) {
                        Log.e(TAG,"onNext");
                        if (expressComBeans.size() < 1) {
                            listener.onError("未查询到相应的快递公司");
                            return;
                        }
                        listener.onSuccess(expressComBeans);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG,"onError");
                        listener.onError(e.getCause().toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG,"onComplete");

                    }
                });

    }

    @Override
    public void queryExpInfo(final String expNum, String com, final QueryListener listener) {
        netWork.getExpressInfo(expNum,com)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ExpressInfoBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e(TAG,"onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull ExpressInfoBean expressInfoBean) {
                        Log.e(TAG,"onNext");
                        if (!(expressInfoBean.getMessage().equals("ok"))) {
                            listener.onError(expressInfoBean.getMessage());
                            return;
                        }else {
                            listener.onSuccess(expressInfoBean);
                            Log.e(TAG, new Gson().toJson(expressInfoBean,ExpressInfoBean.class));
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG,"onError");
                        if (e == null) {
                            listener.onError("未知错误");
                        }else {
                            listener.onError(e.getCause().toString());
                        }
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG,"onComplete");

                    }
                });
    }

    @Override
    public void saveExpressInfo(ExpressInfoBean expressInfoBean) {

        Box<ExpressEntity> expressEntityBox = DAOUtils.getClassBox(ExpressEntity.class);

        ExpressEntity entity = new ExpressEntity(0
                ,expressInfoBean.getNu()
                ,expressInfoBean.getCom()
                ,new Gson().toJson(expressInfoBean.getData())
                ,System.currentTimeMillis()
                ,"not_checked"
                ,null
        );

        expressEntityBox.put(entity);

    }
}
