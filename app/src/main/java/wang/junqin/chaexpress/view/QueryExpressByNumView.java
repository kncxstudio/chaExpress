package wang.junqin.chaexpress.view;

import java.util.List;

import wang.junqin.chaexpress.model.bean.ExpressComBean;

/**
 * Created by KN on 2017/5/29.
 */

public interface QueryExpressByNumView {

    void queryByNum();

    void queryWhileNumIsChanged();

    //将已经上次存入的单号和剪切板的内容进行比较
    void compareWithClipboardContent();


    void showAutoQueryDialog();

    void autoQuery();

    String getEditTextContent();
    void setEditTextContent();
    String getExpressCom();
    void setExpressCom();

    void showToast(String str);

    void queryComplete();

    void showChooseComDialog(List<ExpressComBean> comList);
}
