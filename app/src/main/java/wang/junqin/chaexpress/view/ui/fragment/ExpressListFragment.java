package wang.junqin.chaexpress.view.ui.fragment;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.reflect.TypeToken;
import com.objectbox.gen.ExpressEntity_;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.annotation.Entity;
import io.objectbox.query.Query;
import wang.junqin.chaexpress.DAO.DAOUtils;
import wang.junqin.chaexpress.DAO.ExpressEntity;
import wang.junqin.chaexpress.R;
import wang.junqin.chaexpress.adapter.ExpressItemAdapter;
import wang.junqin.chaexpress.adapter.RecyclerViewItemClickListener;
import wang.junqin.chaexpress.model.impl.Express;
import wang.junqin.chaexpress.presenter.ExpressListPresenter;
import wang.junqin.chaexpress.view.ExpressListView;

/**
 * Created by KN on 2017/6/2.
 */

public class ExpressListFragment extends Fragment implements ExpressListView {

    private String TAG = "ExpressListFragment";

    SwipeRefreshLayout layout;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    ExpressItemAdapter adapter;
    ExpressListPresenter presenter = new ExpressListPresenter(this);

    public int PACKAGES_MODE = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.viewpager_not_checked,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layout = (SwipeRefreshLayout) view.findViewById(R.id.viewpager_not_checked_swiperefreshlayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.viewpager_not_checked_recyclerview);
        recyclerView.setLayoutManager(linearLayoutManager);

        presenter.refreshList(PACKAGES_MODE);

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e(TAG,"setUserVisibleHint " + isVisibleToUser);
        getUserVisibleHint();
        if (recyclerView == null) return;
        if (isVisibleToUser)
            presenter.refreshList(PACKAGES_MODE);

    }


    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return layout;
    }

    @Override
    public ExpressItemAdapter getItemAdapter() {
        return adapter;
    }

    @Override
    public void refreshList(List<ExpressEntity> list) {
        if (adapter != null) {
            adapter.removeAll();
            adapter.addAll(list);
        }else {
            adapter = new ExpressItemAdapter(list);
        }


        Log.e(TAG," list size == " + list.size());
        if (recyclerView.getAdapter() == null){
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view) {
                    ExpressEntity entity = (ExpressEntity) view.getTag();
                    Log.e(TAG,entity.getExpNum());
                }

                @Override
                public void onItemLongClick(View view) {

                    ExpressEntity entity = (ExpressEntity) view.getTag();
                    entity.setStatus("is_checked");
                    DAOUtils.getClassBox(ExpressEntity.class).put(entity);
                }
            });
        }else {
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void showItemDetails() {

    }

    @Override
    public void onItemLongPressed() {

    }



    public static ExpressListFragment newInstance(int mode){
        ExpressListFragment fragment = new ExpressListFragment();
        fragment.PACKAGES_MODE = mode;
        return fragment;
    }


}
