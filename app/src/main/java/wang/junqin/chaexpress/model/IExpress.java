package wang.junqin.chaexpress.model;

import wang.junqin.chaexpress.presenter.QueryListener;

/**
 * Created by KN on 2017/5/29.
 */

public interface IExpress {
    public void queryExpCom(String expNum,QueryListener listener);
    public void queryExpInfo(String expNum, String com, QueryListener listener);

}

