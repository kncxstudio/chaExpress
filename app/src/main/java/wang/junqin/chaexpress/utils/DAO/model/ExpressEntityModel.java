package wang.junqin.chaexpress.utils.DAO.model;


import android.support.annotation.MainThread;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.objectbox.gen.ExpressEntity_;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.android.AndroidScheduler;
import io.objectbox.annotation.Entity;
import io.objectbox.query.Query;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import wang.junqin.chaexpress.model.IExpress;
import wang.junqin.chaexpress.model.bean.ExpressInfoBean;
import wang.junqin.chaexpress.model.impl.Express;
import wang.junqin.chaexpress.presenter.ExpressListPresenter;
import wang.junqin.chaexpress.utils.DAO.DAOUtils;
import wang.junqin.chaexpress.utils.DAO.ExpressEntity;
import wang.junqin.chaexpress.utils.MyUtils;

/**
 * Created by KN on 2017/6/2.
 */

public class ExpressEntityModel {

    Object presenter;
    public ExpressEntityModel(){}

    public ExpressEntityModel(Object presenter){
        this.presenter = presenter;
    }

    IExpress express = new Express();

    Box<ExpressEntity> expressEntityBox = DAOUtils.getClassBox(ExpressEntity.class);

    public List<ExpressEntity> getAllEntities(){

        Log.e("model","getAllEntities");
        Query<ExpressEntity> query = expressEntityBox
                .query()
                .orderDesc(ExpressEntity_.dateAdded)
                .build();
        List<ExpressEntity> entityList = new ArrayList<>();
        entityList.addAll(query.find());

        return entityList;
    }

    public List<ExpressEntity> getNotCheckedEntities(){

        Query<ExpressEntity> query = expressEntityBox
                .query()
                .equal(ExpressEntity_.status,"not_checked")
                .orderDesc(ExpressEntity_.dateAdded)
                .build();
        List<ExpressEntity> entityList = new ArrayList<>();
        entityList.addAll(query.find());
        return entityList;
    }

    public List<ExpressEntity> getIsCheckedEntities(){
        Query<ExpressEntity> query = expressEntityBox
                .query()
                .equal(ExpressEntity_.status,"is_checked")
                .orderDesc(ExpressEntity_.dateAdded)
                .build();
        List<ExpressEntity> entityList = new ArrayList<>();
        entityList.addAll(query.find());
        return entityList;
    }

    public ExpressEntity getEntityByExpNum(String expNum){
        Query<ExpressEntity> query = expressEntityBox
                .query()
                .equal(ExpressEntity_.expNum,expNum)
                .build();
        ExpressEntity entity = query.find().get(0);
        return entity;
    }

    public void remove(ExpressEntity entity){
        expressEntityBox.remove(entity);
    }

    public void put(ExpressEntity entity){
        Query<ExpressEntity> query = expressEntityBox
                .query()
                .equal(ExpressEntity_.expNum,entity.getExpNum())
                .orderDesc(ExpressEntity_.dateAdded)
                .build();
        List<ExpressEntity> list = query.find();

        if (list.size()>0)return;

        expressEntityBox.put(entity);

    }

    public void edit(ExpressEntity entity){
        Query<ExpressEntity> query = expressEntityBox
                .query()
                .equal(ExpressEntity_.expNum,entity.getExpNum())
                .orderDesc(ExpressEntity_.dateAdded)
                .build();
        List<ExpressEntity> list = query.find();

        if (list.size()>0){
            ExpressEntity temp = list.get(0);
            temp.setRemark(entity.getRemark());
            temp.setExpInfo(entity.getExpInfo());
            temp.setStatus(entity.getStatus());

            expressEntityBox.put(temp);
        }
    }


    public void updateExpListFromNet(List<ExpressEntity> list){
        express.updateAllExpEntityFromNet(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ExpressInfoBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ExpressInfoBean expressInfoBean) {
                        ExpressEntity entity = new ExpressEntity(
                                0
                                ,expressInfoBean.getNu()
                                ,expressInfoBean.getCom()
                                ,new Gson().toJson(expressInfoBean.getData())
                                ,System.currentTimeMillis()
                                ,null
                                ,null
                        );

                        edit(entity);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("EntityModel","onError" + e.getCause().toString());
                    }

                    @Override
                    public void onComplete() {
                        ((ExpressListPresenter) presenter).finishRefresh();
                    }
                });
    }
}
