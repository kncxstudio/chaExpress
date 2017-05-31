package wang.junqin.chaexpress.ui.fragment;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

import wang.junqin.chaexpress.ExpressApplication;
import wang.junqin.chaexpress.R;
import wang.junqin.chaexpress.data.ComCodeNameMap;
import wang.junqin.chaexpress.data.FLAGS;
import wang.junqin.chaexpress.view.QueryExpressByNumView;

/**
 * Created by KN on 2017/5/30.
 */

public class ChooseComDialogFragment extends DialogFragment {
    ListView listView;
    QueryExpressByNumView view;





    public static ChooseComDialogFragment newInstance(QueryExpressByNumView view){
        ChooseComDialogFragment chooseComDialogFragment = new ChooseComDialogFragment();
        chooseComDialogFragment.view = view;
        return chooseComDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_exp_com_dialog,container,false);
        getDialog().setTitle("请选择快递公司");
        listView = (ListView) view.findViewById(R.id.choose_com_dialog_listview);
        listView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        final ArrayList<String> comCodeList = getArguments().getStringArrayList("data");

        ArrayList<String> comNameList = new ArrayList<>();

        for (String code : comCodeList)
            comNameList.add(ComCodeNameMap.getComNameByCode(code));
        final ListAdapter adapter = new ArrayAdapter<>(ExpressApplication.getContext(),android.R.layout.simple_list_item_1,comNameList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("Dialog","Position" + position);
                Intent intent = new Intent();
                intent.putExtra("comCode",comCodeList.get(position));
                getTargetFragment().onActivityResult(1, FLAGS.RETURN_COM_CODE,intent);
                dismiss();
            }
        });

        listView.setAdapter(adapter);
        return view;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("Dialog","Dialog关闭");
        view.queryComplete();
    }
}
