package wang.junqin.chaexpress.view;

import wang.junqin.chaexpress.utils.DAO.ExpressEntity;

/**
 * Created by KN on 2017/6/4.
 */

public interface ExpressInfoDetailsView {
    public void refreshExpInfo(ExpressEntity entity);
    public String getExpRemark();
    public void setExpRemark(String str);
    public String getExpNum();
    public void copyExpNumToClipboard();
    public int getNetworkStatus();
}
