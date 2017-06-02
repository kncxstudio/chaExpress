package wang.junqin.chaexpress.view.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import wang.junqin.chaexpress.R;
import wang.junqin.chaexpress.adapter.ExpressPagerAdapter;
import wang.junqin.chaexpress.view.ExpressListView;

/**
 * Created by KN on 2017/5/30.
 */

public class MyPackagesFragment extends Fragment {

    TabLayout tabLayout;
    FragmentManager fragmentManager;
    ViewPager viewPager;
    ExpressPagerAdapter pagerAdapter;
    List<Fragment> fragmentList;
    ArrayList<String> titleList = new ArrayList<>();

    NotCheckedFragment notCheckedFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_packages,container,false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        tabLayout = (TabLayout) view.findViewById(R.id.my_packages_tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.my_packages_viewpager);
        super.onResume();

        titleList.add("在运包裹");
        titleList.add("已签收包裹");

        for (String title : titleList)
            tabLayout.addTab(tabLayout.newTab().setText(title));

        fragmentManager = getFragmentManager();
        fragmentList = new ArrayList<>();
        fragmentList.add(notCheckedFragment = new NotCheckedFragment());
        fragmentList.add(new QueryFragment());
        pagerAdapter = ExpressPagerAdapter.newInstance(fragmentManager,fragmentList);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = titleList.indexOf(tab.getText().toString());
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabLayout.setScrollPosition(position,positionOffset,true);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);

    }


    public void refreshChild(){
        notCheckedFragment.setUserVisibleHint(true);
    }

}
