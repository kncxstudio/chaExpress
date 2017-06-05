package wang.junqin.chaexpress.view.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by KN on 2017/6/1.
 */

public class ExpressPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList;

    public ExpressPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    public static ExpressPagerAdapter newInstance(FragmentManager fm,List<Fragment> fragmentList){
        ExpressPagerAdapter adapter = new ExpressPagerAdapter(fm);
        adapter.fragmentList = fragmentList;
        return adapter;
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void removeAllPages(){
        fragmentList.clear();
        notifyDataSetChanged();
    }
}
