package wang.junqin.chaexpress.view.ui.fragment;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dd.CircularProgressButton;

import java.util.ArrayList;
import java.util.List;

import wang.junqin.chaexpress.R;
import wang.junqin.chaexpress.data.FLAGS;
import wang.junqin.chaexpress.model.bean.ExpressComBean;
import wang.junqin.chaexpress.presenter.ExpressQueryPresenter;
import wang.junqin.chaexpress.utils.MyUtils;
import wang.junqin.chaexpress.view.QueryExpressByNumView;

/**
 * Created by KN on 2017/5/29.
 */

public class QueryFragment extends Fragment implements QueryExpressByNumView {


    ExpressQueryPresenter presenter = new ExpressQueryPresenter(this);
    EditText expressNumET;
    CircularProgressButton querySunmitBtn;

    Handler handler = new Handler();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_express_query,container,false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        expressNumET = (EditText) view.findViewById(R.id.express_num_ET);
        querySunmitBtn = (CircularProgressButton) view.findViewById(R.id.query_submit_Btn);

        querySunmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                querySunmitBtn.setProgress(50);
                querySunmitBtn.setIndeterminateProgressMode(true);
                presenter.queryExpCom();
            }
        });



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case FLAGS.DIALOG_RETURN_CODE:
                String comCode = data.getStringExtra("item");
                if (comCode != null) {
                    presenter.queryExpInfo(comCode);
                }else {
                    queryComplete();
                }
                break;
        }
    }

    @Override
    public void queryByNum() {

    }

    @Override
    public void queryWhileNumIsChanged() {

    }

    @Override
    public void compareWithClipboardContent() {

    }

    @Override
    public void showAutoQueryDialog() {

    }

    @Override
    public void autoQuery() {

    }

    @Override
    public String getEditTextContent() {
        return expressNumET.getText().toString();
    }

    @Override
    public void setEditTextContent() {

    }

    @Override
    public String getExpressCom() {
        return null;
    }

    @Override
    public void setExpressCom() {

    }

    @Override
    public void showToast(String str) {
        MyUtils.showToast(str);
    }

    @Override
    public void queryComplete() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                querySunmitBtn.setProgress(0);
            }
        });
    }


    @Override
    public void showChooseComDialog(List<ExpressComBean> comList) {
        ArrayList<String> comCodeList = new ArrayList<>();
        for (ExpressComBean com : comList) comCodeList.add(com.getComCode());

        ChooseDialogFragment dialogFragment = ChooseDialogFragment.newInstance();
        Bundle data = new Bundle();
        data.putStringArrayList("data",comCodeList);
        data.putString("title",FLAGS.CHOOSE_EXPRESS_COMPANY);
        dialogFragment.setArguments(data);
        dialogFragment.setTargetFragment(this,1);
        dialogFragment.show(getFragmentManager(),"ChooseDialog");
    }
}
