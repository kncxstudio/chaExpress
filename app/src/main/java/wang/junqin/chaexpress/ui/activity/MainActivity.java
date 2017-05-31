package wang.junqin.chaexpress.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.List;

import wang.junqin.chaexpress.R;
import wang.junqin.chaexpress.model.bean.ExpressComBean;
import wang.junqin.chaexpress.ui.fragment.ExpressListFragment;
import wang.junqin.chaexpress.ui.fragment.QueryFragment;
import wang.junqin.chaexpress.utils.MyUtils;

public class MainActivity extends AppCompatActivity {

    QueryFragment queryFragment;
    ExpressListFragment expressListFragment;
    private Context context = this;

    private String TAG = this.getClass().getName();
    private Handler handler = new Handler();
    private FragmentManager fm;
    private Toolbar toolbar;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_query:
                    Log.e(TAG, "nav_query");
                    fragmentTransaction.show(queryFragment);
                    fragmentTransaction.hide(expressListFragment);
                    break;
                case R.id.navigation_my_packages:
                    Log.e(TAG, "nav_packages");
                    fragmentTransaction.show(expressListFragment);
                    fragmentTransaction.hide(queryFragment);
                    break;
            }
            fragmentTransaction.commit();
            return true;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        fm = getFragmentManager();
        queryFragment = new QueryFragment();
        expressListFragment = new ExpressListFragment();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.content, queryFragment);
        fragmentTransaction.add(R.id.content, expressListFragment);

        fragmentTransaction.hide(expressListFragment);

        fragmentTransaction.commit();

    }

    void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getTitle());
        setSupportActionBar(toolbar);

    }


}
