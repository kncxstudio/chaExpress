package wang.junqin.chaexpress.presenter;

import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.util.List;

import io.objectbox.annotation.Entity;
import wang.junqin.chaexpress.model.IExpress;
import wang.junqin.chaexpress.model.bean.ExpressComBean;
import wang.junqin.chaexpress.model.bean.ExpressInfoBean;
import wang.junqin.chaexpress.model.impl.Express;
import wang.junqin.chaexpress.utils.DAO.ExpressEntity;
import wang.junqin.chaexpress.utils.DAO.model.ExpressEntityModel;
import wang.junqin.chaexpress.utils.MyUtils;
import wang.junqin.chaexpress.view.QueryExpressByNumView;

/**
 * Created by KN on 2017/5/29.
 */

public class ExpressQueryPresenter {
    QueryExpressByNumView view;
    IExpress express;
    ExpressEntityModel model;

    public ExpressQueryPresenter(QueryExpressByNumView queryExpressByNumView){
        this.view = queryExpressByNumView;
        express = new Express();
        model = new ExpressEntityModel();
    }

    public void queryExpCom(){
        express.queryExpCom(view.getEditTextContent(), new QueryListener() {
            @Override
            public void onSuccess(List<ExpressComBean> expressComBeans) {
                if (expressComBeans.size() > 0){
                    view.showChooseComDialog(expressComBeans);
                }
            }

            @Override
            public void onSuccess(ExpressInfoBean expressInfoBean) {

            }

            @Override
            public void onError(@Nullable String str) {
                view.queryComplete();

                assert str != null;
                if (str.contains("No address associated with hostname")){
                    view.showToast("连接服务器失败，请检查网络");
                }else{
                    view.showToast(str);
                }

            }

        });
    }

    public void queryExpInfo(String comCode){
        express.queryExpInfo(view.getEditTextContent(), comCode, new QueryListener() {
            @Override
            public void onSuccess(List<ExpressComBean> expressComBeans) {
            }

            @Override
            public void onSuccess(ExpressInfoBean expressInfoBean) {
                express.saveExpressInfo(expressInfoBean);
                MyUtils.showToast("添加快递成功!");
                view.queryComplete();
            }

            @Override
            public void onError(@Nullable String str) {
                MyUtils.showToast(str);
                view.queryComplete();
            }
        });
    }


    public void saveEntity(ExpressEntity entity){
        model.put(entity);
    }


}
