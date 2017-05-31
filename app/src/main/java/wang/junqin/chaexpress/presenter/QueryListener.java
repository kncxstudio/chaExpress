package wang.junqin.chaexpress.presenter;

import android.support.annotation.Nullable;

import java.util.List;

import wang.junqin.chaexpress.model.bean.ExpressComBean;
import wang.junqin.chaexpress.model.bean.ExpressInfoBean;
import wang.junqin.chaexpress.model.impl.Express;

/**
 * Created by KN on 2017/5/29.
 */

public interface QueryListener {
    void onSuccess(List<ExpressComBean> expressComBeans);
    void onSuccess(ExpressInfoBean expressInfoBean);
    void onError(@Nullable String str);
}
