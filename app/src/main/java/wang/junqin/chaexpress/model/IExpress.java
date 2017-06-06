package wang.junqin.chaexpress.model;

import java.util.List;

import io.reactivex.Observable;
import wang.junqin.chaexpress.model.bean.ExpressInfoBean;
import wang.junqin.chaexpress.presenter.QueryListener;
import wang.junqin.chaexpress.utils.DAO.ExpressEntity;

/**
 * Created by KN on 2017/5/29.
 */

public interface IExpress {
    public void queryExpCom(String expNum,QueryListener listener);
    public void queryExpInfo(String expNum, String com, QueryListener listener);
    public void saveExpressInfo(ExpressInfoBean expressInfoBean);
    public Observable<ExpressInfoBean> updateAllExpEntityFromNet(List<ExpressEntity> list);
}

