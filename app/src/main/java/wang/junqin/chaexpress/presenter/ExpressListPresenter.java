package wang.junqin.chaexpress.presenter;

import android.util.Log;

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

}
