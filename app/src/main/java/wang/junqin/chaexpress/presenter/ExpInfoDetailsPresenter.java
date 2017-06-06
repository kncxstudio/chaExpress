package wang.junqin.chaexpress.presenter;

import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.util.List;

import io.objectbox.annotation.Entity;
import wang.junqin.chaexpress.data.FLAGS;
import wang.junqin.chaexpress.model.IExpress;
import wang.junqin.chaexpress.model.bean.ExpressComBean;
import wang.junqin.chaexpress.model.bean.ExpressInfoBean;
import wang.junqin.chaexpress.model.impl.Express;
import wang.junqin.chaexpress.utils.DAO.ExpressEntity;
import wang.junqin.chaexpress.utils.DAO.model.ExpressEntityModel;
import wang.junqin.chaexpress.utils.MyUtils;
import wang.junqin.chaexpress.view.ExpressInfoDetailsView;

/**
 * Created by KN on 2017/6/5.
 */

public class ExpInfoDetailsPresenter {
    ExpressInfoDetailsView view;
    ExpressEntityModel entityModel;
    IExpress expressModel;
    public ExpInfoDetailsPresenter(ExpressInfoDetailsView view){
        this.view = view;
        entityModel = new ExpressEntityModel();
        expressModel = new Express();
    }


    public void updateExpInfoFromNetwork(final ExpressEntity entity){
        expressModel.queryExpInfo(entity.getExpNum(), entity.getExpCom(), new QueryListener() {
            @Override
            public void onSuccess(List<ExpressComBean> expressComBeans) {

            }

            @Override
            public void onSuccess(ExpressInfoBean expressInfoBean) {
                entity.setExpInfo(new Gson().toJson(expressInfoBean.getData()));
                entityModel.put(entity);
                view.refreshExpInfo(entity);
                view.setNetworkStatus(FLAGS.NETWORK_NOT_BUSY);
                MyUtils.showToast("物流信息更新成功");
            }

            @Override
            public void onError(@Nullable String str) {
                MyUtils.showToast(str);
                view.setNetworkStatus(FLAGS.NETWORK_NOT_BUSY);

            }
        });
    }

    public ExpressEntity getEntityByExpNum(String expNum){
        return entityModel.getEntityByExpNum(expNum);
    }

    public void save(ExpressEntity entity){
        entityModel.put(entity);
    }
}
