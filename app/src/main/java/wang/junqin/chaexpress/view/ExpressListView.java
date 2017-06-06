package wang.junqin.chaexpress.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import wang.junqin.chaexpress.utils.DAO.ExpressEntity;
import wang.junqin.chaexpress.view.ui.adapter.ExpressItemAdapter;

/**
 * Created by KN on 2017/5/30.
 */

public interface ExpressListView {

    RecyclerView getRecyclerView();

    SwipeRefreshLayout getSwipeRefreshLayout();

    ExpressItemAdapter getItemAdapter();

    int getViewPackagesMode();
    void refreshList(List<ExpressEntity> list);

    void showItemDetails();

    void onItemLongPressed();

    void showLongPressedActionChooseDialog(ArrayList<String> actionList);
    void showToast(String str);

    void finishRefresh();

}
