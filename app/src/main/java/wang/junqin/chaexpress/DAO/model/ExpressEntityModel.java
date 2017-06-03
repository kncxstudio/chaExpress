package wang.junqin.chaexpress.DAO.model;


import android.util.Log;

import com.objectbox.gen.ExpressEntity_;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.annotation.Entity;
import io.objectbox.query.Query;
import io.objectbox.query.QueryBuilder;
import wang.junqin.chaexpress.DAO.DAOUtils;
import wang.junqin.chaexpress.DAO.ExpressEntity;
import wang.junqin.chaexpress.model.impl.Express;

/**
 * Created by KN on 2017/6/2.
 */

public class ExpressEntityModel {

    Box<ExpressEntity> expressEntityBox = DAOUtils.getClassBox(ExpressEntity.class);

    public List<ExpressEntity> getAllEntities(){
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


    public void remove(ExpressEntity entity){
        expressEntityBox.remove(entity);
    }

    public void put(ExpressEntity entity){
        expressEntityBox.put(entity);
    }
}
