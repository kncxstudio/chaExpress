package wang.junqin.chaexpress.view.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import wang.junqin.chaexpress.R;
import wang.junqin.chaexpress.data.VIEW_HOLDER_TYPE_FLAGS;

/**
 * Created by KN on 2017/6/5.
 */

public class MutiTypeAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    List<Object> list;

    public MutiTypeAdapter(List<Object> list){
        this.list = list;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }



}


class BaseViewHolder extends RecyclerView.ViewHolder{
    int VIEW_HOLDER_TYPE = 0;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public void setViewHolderType(int type){
        this.VIEW_HOLDER_TYPE = type;
    }

    public int getViewHolderType(){
        return VIEW_HOLDER_TYPE;
    }
}


class GroupNameViewHolder extends BaseViewHolder{
    TextView groupName;
    public GroupNameViewHolder(View itemView) {
        super(itemView);
        this.VIEW_HOLDER_TYPE = VIEW_HOLDER_TYPE_FLAGS.GROUP_TYPE;
        groupName = (TextView) itemView.findViewById(R.id.item_group_name_tv);
    }
}



