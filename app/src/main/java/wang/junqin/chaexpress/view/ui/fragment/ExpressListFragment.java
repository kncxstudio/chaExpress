package wang.junqin.chaexpress.view.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import wang.junqin.chaexpress.utils.DAO.ExpressEntity;
import wang.junqin.chaexpress.R;
import wang.junqin.chaexpress.view.ui.adapter.ExpressItemAdapter;
import wang.junqin.chaexpress.view.ui.adapter.RecyclerViewItemClickListener;
import wang.junqin.chaexpress.data.ACTION_FLAGS;
import wang.junqin.chaexpress.data.FLAGS;
import wang.junqin.chaexpress.presenter.ExpressListPresenter;
import wang.junqin.chaexpress.utils.MyUtils;
import wang.junqin.chaexpress.view.ExpressListView;
import wang.junqin.chaexpress.view.ui.activity.ExpInfoDetailsActivity;

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
    ExpressEntity selectedEntity = null;
    public int PACKAGES_MODE = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_express_list,container,false);
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
    public void onResume() {
        super.onResume();
        presenter.refreshList(PACKAGES_MODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FLAGS.DIALOG_RETURN_CODE){
            if (data.getStringExtra("item") != null) {
                String action = data.getStringExtra("item");
                switch (action) {
                    case ACTION_FLAGS.DELETE_ITEM:
                        presenter.deleteExpressInfo(selectedEntity);
                        break;
                    case ACTION_FLAGS.CHANGE_STATUS_TO_IS_CHECKED:
                        presenter.editExpressInfo(selectedEntity);
                        break;
                    case ACTION_FLAGS.SHARE_TO_FRIENDS:

                        break;
                }
            }
        }
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
    public int getViewPackagesMode() {
        return PACKAGES_MODE;
    }

    @Override
    public void refreshList(List<ExpressEntity> list) {
        if (adapter != null) {
            adapter.removeAll();
            adapter.addAll(list);
        }else {
            adapter = new ExpressItemAdapter(list);
        }


        if (recyclerView.getAdapter() == null){
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view) {
                    ExpressEntity entity = (ExpressEntity) view.getTag();
                    Intent intent = new Intent(getContext(),ExpInfoDetailsActivity.class);
                    intent.putExtra(FLAGS.EXP_NUM,entity.getExpNum());
                    startActivity(intent);
                    Log.e(TAG,entity.getExpNum());
                }

                @Override
                public void onItemLongClick(View view) {
                    //获取到长按item所对应的entity
                    selectedEntity = (ExpressEntity) view.getTag();

                    ArrayList<String> actionList = new ArrayList<>();
                    switch (PACKAGES_MODE){
                        case ExpressListPresenter.IS_CHECKED_PACKAGES:
                            actionList.add(ACTION_FLAGS.SHARE_TO_FRIENDS);
                            actionList.add(ACTION_FLAGS.DELETE_ITEM);
                            break;
                        case ExpressListPresenter.NOT_CHECKED_PACKAGES:
                            actionList.add(ACTION_FLAGS.CHANGE_STATUS_TO_IS_CHECKED);
                            actionList.add(ACTION_FLAGS.SHARE_TO_FRIENDS);
                            actionList.add(ACTION_FLAGS.DELETE_ITEM);
                            break;
                    }

                    showLongPressedActionChooseDialog(actionList);
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

    @Override
    public void showLongPressedActionChooseDialog(ArrayList<String> actionList) {
        ChooseDialogFragment dialogFragment = ChooseDialogFragment.newInstance();
        Bundle data = new Bundle();
        data.putStringArrayList("data",actionList);
        data.putString("title",FLAGS.CHOOSE_EXPRESS_ITEM_ACTION);
        dialogFragment.setArguments(data);
        dialogFragment.setTargetFragment(this,1);
        dialogFragment.show(getFragmentManager(),"ChooseDialog");
    }

    @Override
    public void showToast(String str) {
        MyUtils.showToast(str);
    }


    public static ExpressListFragment newInstance(int mode){
        ExpressListFragment fragment = new ExpressListFragment();
        fragment.PACKAGES_MODE = mode;
        return fragment;
    }


}
