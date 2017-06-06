package wang.junqin.chaexpress.utils.net;

import java.util.List;

import io.reactivex.Observable;
import wang.junqin.chaexpress.model.bean.ExpressComBean;
import wang.junqin.chaexpress.model.bean.ExpressInfoBean;
import wang.junqin.chaexpress.utils.DAO.ExpressEntity;

/**
 * Created by KN on 2017/5/29.
 */

public interface NetWork {
    Observable<List<ExpressComBean>> getExpressCom(String expressNum);
    Observable<ExpressInfoBean> getExpressInfo(String expNum,String comNum);
    Observable<ExpressInfoBean> getExpressInfo(List<ExpressEntity> list);

}
