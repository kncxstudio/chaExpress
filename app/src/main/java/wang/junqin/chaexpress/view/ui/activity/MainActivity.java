package wang.junqin.chaexpress.view.ui.activity;

import android.content.Intent;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import moe.feng.alipay.zerosdk.AlipayZeroSdk;
import wang.junqin.chaexpress.R;
import wang.junqin.chaexpress.data.FLAGS;
import wang.junqin.chaexpress.utils.MyUtils;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_donation:
                if (AlipayZeroSdk.hasInstalledAlipayClient(this)){
                    AlipayZeroSdk.startAlipayClient(this, FLAGS.ALIPAY_QR_CODE_STR);
                }else {
                    MyUtils.showToast("未安装支付宝客户端");
                }
                break;
            case R.id.menu_about:
                startActivity(new Intent(this,AboutActivity.class));
                break;
        }
        return true;
    }

    void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getTitle());
        setSupportActionBar(toolbar);

    }


}
