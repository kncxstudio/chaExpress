package wang.junqin.chaexpress.view.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import wang.junqin.chaexpress.ExpressApplication;
import wang.junqin.chaexpress.R;
import wang.junqin.chaexpress.data.ComCodeNameMap;
import wang.junqin.chaexpress.data.FLAGS;
import wang.junqin.chaexpress.view.QueryExpressByNumView;

/**
 * Created by KN on 2017/5/30.
 */

public class ChooseDialogFragment extends DialogFragment {
    ListView listView;
    Intent intent = new Intent();
    String title;


    public static ChooseDialogFragment newInstance(){
        return new ChooseDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_exp_com_dialog,container,false);
        getDialog().setTitle(title = getArguments().getString("title"));
        listView = (ListView) view.findViewById(R.id.choose_com_dialog_listview);
        listView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        final ArrayList<String> itemList = getArguments().getStringArrayList("data");

        ArrayList<String> comNameList = new ArrayList<>();
        final ListAdapter adapter;

        switch (title){
            case FLAGS.CHOOSE_EXPRESS_COMPANY:
                for (String code : itemList)
                    comNameList.add(ComCodeNameMap.getComNameByCode(code));
                adapter = new ArrayAdapter<>(ExpressApplication.getContext(),android.R.layout.simple_list_item_1,comNameList);
                listView.setAdapter(adapter);
                break;
            case FLAGS.CHOOSE_EXPRESS_ITEM_ACTION:
                adapter = new ArrayAdapter<>(ExpressApplication.getContext(),android.R.layout.simple_list_item_1,itemList);
                listView.setAdapter(adapter);
                break;
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("Dialog","Position" + position);
                intent.putExtra("item",itemList.get(position));
                dismiss();
            }
        });


        return view;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getTargetFragment().onActivityResult(1, FLAGS.DIALOG_RETURN_CODE,intent);
        Log.e("Dialog","Dialog关闭");

    }
}
