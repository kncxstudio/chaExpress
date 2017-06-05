package wang.junqin.chaexpress.utils.DAO.model;


import com.objectbox.gen.ExpressEntity_;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;
import io.objectbox.query.Query;
import wang.junqin.chaexpress.utils.DAO.DAOUtils;
import wang.junqin.chaexpress.utils.DAO.ExpressEntity;

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
        expressEntityBox.put(entity);
    }
}
