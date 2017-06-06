package wang.junqin.chaexpress.presenter;

import android.util.Log;

import java.util.List;

import wang.junqin.chaexpress.utils.DAO.ExpressEntity;
import wang.junqin.chaexpress.utils.DAO.model.ExpressEntityModel;
import wang.junqin.chaexpress.view.ExpressListView;

/**
 * Created by KN on 2017/6/2.
 */

public class ExpressListPresenter {
    ExpressListView view;
    ExpressEntityModel model = new ExpressEntityModel(this);

    public final static int ALL_PACKAGES = 0;
    public final static int IS_CHECKED_PACKAGES = 1;
    public final static int NOT_CHECKED_PACKAGES = 2;

    public ExpressListPresenter(ExpressListView view){
        this.view = view;
    }

    public void refreshList(int refreshMode){
        Log.e("presenter","refreshMode = " + refreshMode);
        switch (refreshMode){
            case ALL_PACKAGES:
                view.refreshList(model.getAllEntities());
                break;
            case IS_CHECKED_PACKAGES:
                view.refreshList(model.getIsCheckedEntities());
                break;
            case NOT_CHECKED_PACKAGES:
                view.refreshList(model.getNotCheckedEntities());
                break;
        }
    }

    public void editExpressInfo(ExpressEntity entity){
        entity.setStatus("is_checked");
        model.edit(entity);
        view.refreshList(model.getNotCheckedEntities());
        view.showToast("将包裹标记为已签收状态");

    }

    public void shreToFriends(ExpressEntity entity){

    }

    public void deleteExpressInfo(ExpressEntity entity){
        model.remove(entity);
        view.showToast("删除包裹物流信息成功！");
        switch (view.getViewPackagesMode()){
            case ExpressListPresenter.IS_CHECKED_PACKAGES:
                view.refreshList(model.getIsCheckedEntities());
                break;
            case ExpressListPresenter.NOT_CHECKED_PACKAGES:
                view.refreshList(model.getNotCheckedEntities());
                break;
        }
    }


    public void updateAllExpInfo(){
        Log.e("presenter","updateAllExpInfo");
        model.updateExpListFromNet(model.getAllEntities());
    }

    public void finishRefresh(){
        view.finishRefresh();
    }


}
