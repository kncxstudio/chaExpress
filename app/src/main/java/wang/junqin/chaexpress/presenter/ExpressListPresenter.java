package wang.junqin.chaexpress.presenter;

import android.util.Log;

import wang.junqin.chaexpress.DAO.DAOUtils;
import wang.junqin.chaexpress.DAO.ExpressEntity;
import wang.junqin.chaexpress.DAO.model.ExpressEntityModel;
import wang.junqin.chaexpress.view.ExpressListView;

/**
 * Created by KN on 2017/6/2.
 */

public class ExpressListPresenter {
    ExpressListView view;
    ExpressEntityModel model = new ExpressEntityModel();

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
        model.put(entity);
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

}
