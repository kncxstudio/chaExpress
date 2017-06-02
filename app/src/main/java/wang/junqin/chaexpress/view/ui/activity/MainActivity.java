package wang.junqin.chaexpress.view.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import wang.junqin.chaexpress.R;
import wang.junqin.chaexpress.view.ui.fragment.MyPackagesFragment;
import wang.junqin.chaexpress.view.ui.fragment.QueryFragment;

public class MainActivity extends AppCompatActivity {

    QueryFragment queryFragment;
    MyPackagesFragment myPackagesFragment;
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
                    fragmentTransaction.hide(myPackagesFragment);
                    fragmentTransaction.show(queryFragment);
                    break;
                case R.id.navigation_my_packages:
                    Log.e(TAG, "nav_packages");
                    fragmentTransaction.hide(queryFragment);
                    fragmentTransaction.show(myPackagesFragment);
                    myPackagesFragment.refreshChild();
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


        fm = getSupportFragmentManager();
        queryFragment = new QueryFragment();
        myPackagesFragment = new MyPackagesFragment();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.content,queryFragment,"query_packages");
        fragmentTransaction.add(R.id.content,myPackagesFragment,"my_packages");
        fragmentTransaction.hide(myPackagesFragment);
        fragmentTransaction.commit();
    }



    @Override
    protected void onResume() {
        super.onResume();

    }

    void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getTitle());
        setSupportActionBar(toolbar);

    }


}
