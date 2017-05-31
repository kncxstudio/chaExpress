package wang.junqin.chaexpress.ui.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import wang.junqin.chaexpress.R;
import wang.junqin.chaexpress.view.ExpressListView;

/**
 * Created by KN on 2017/5/30.
 */

public class ExpressListFragment extends Fragment implements ExpressListView {

    TabLayout tabLayout;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    String[] titleList = {"在运包裹","已签收包裹"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_packages,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        tabLayout = (TabLayout) view.findViewById(R.id.my_packages_tablayout);
        for (String title : titleList)
            tabLayout.addTab(tabLayout.newTab().setText(title));


        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

    }

    @Override
    public void refreshList() {

    }

    @Override
    public void showItemDetails() {

    }

    @Override
    public void longPressed() {

    }
}
