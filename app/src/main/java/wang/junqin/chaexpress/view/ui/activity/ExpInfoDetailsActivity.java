package wang.junqin.chaexpress.view.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import wang.junqin.chaexpress.utils.DAO.ExpressEntity;
import wang.junqin.chaexpress.R;
import wang.junqin.chaexpress.view.ui.adapter.ExpressInfoDetailsAdapter;
import wang.junqin.chaexpress.data.ComCodeNameMap;
import wang.junqin.chaexpress.data.FLAGS;
import wang.junqin.chaexpress.model.bean.ExpressInfoBean;
import wang.junqin.chaexpress.presenter.ExpInfoDetailsPresenter;
import wang.junqin.chaexpress.view.ExpressInfoDetailsView;

/**
 * Created by KN on 2017/6/4.
 */

public class ExpInfoDetailsActivity extends AppCompatActivity implements ExpressInfoDetailsView {

    private ExpressEntity entity;
    private Toolbar toolbar;
    private TextView expRemarkTV,expNumAndComTV;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private RecyclerView recyclerView;
    private List<ExpressInfoBean.Data> expInfoList;
    private ExpressInfoDetailsAdapter adapter;

    private ExpInfoDetailsPresenter presenter = new ExpInfoDetailsPresenter(this);


    private int NETWORK_STATUS = FLAGS.NETWORK_NOT_BUSY;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(this.getClass().getName(),"onCreate");
        String expNum = getIntent().getStringExtra(FLAGS.EXP_NUM);
        entity = presenter.getEntityByExpNum(expNum);
        setContentView(R.layout.activity_express_info_details);
        init();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exp_details_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_details_refresh:
                presenter.updateExpInfoFromNetwork(entity);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void refreshExpInfo(ExpressEntity entity) {
        if (adapter != null)adapter.setData((List<ExpressInfoBean.Data>) new Gson().fromJson(entity.getExpInfo(),new TypeToken<List<ExpressInfoBean.Data>>(){}.getType()));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                assert adapter != null;
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public String getExpRemark() {
        return null;
    }

    @Override
    public void setExpRemark(String str) {

    }

    @Override
    public String getExpNum() {
        return null;
    }

    @Override
    public void copyExpNumToClipboard() {

    }

    @Override
    public int getNetworkStatus() {
        return NETWORK_STATUS;
    }


    void init(){
        toolbar = (Toolbar) findViewById(R.id.toolbar_details);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        expRemarkTV = (TextView) findViewById(R.id.activity_details_exp_remark);
        expNumAndComTV = (TextView) findViewById(R.id.activity_details_exp_num_and_com);

        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setTitle("物流详情");
        collapsingToolbarLayout.setExpandedTitleColor(Color.parseColor("#00000000"));
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#ffffff"));
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setBackgroundColor(Color.parseColor("#ffffff"));


        //显示备注和订单号
        String comName = ComCodeNameMap.getComNameByCode(entity.getExpCom());
        if (entity.getRemark() != null){
            expRemarkTV.setText(entity.getRemark());
        }else {
            int expNumLength = entity.getExpNum().length();
            String expNumLast4Char = entity.getExpNum().substring(expNumLength-4,expNumLength);
            expRemarkTV.setText(comName + " " + expNumLast4Char);
        }
        expNumAndComTV.setText(entity.getExpNum() + "(" + comName + ")");


        //初始化RecyclerView和Adapter
        expInfoList = new Gson().fromJson(entity.getExpInfo(), new TypeToken<List<ExpressInfoBean.Data>>(){}.getType());
        recyclerView = (RecyclerView) findViewById(R.id.activity_details_reyclcrview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExpressInfoDetailsAdapter(expInfoList);
        Log.e("size",expInfoList.size()+"");
        recyclerView.setAdapter(adapter);
    }

}
