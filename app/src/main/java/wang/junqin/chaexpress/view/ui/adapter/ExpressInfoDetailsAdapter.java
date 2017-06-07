package wang.junqin.chaexpress.view.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import wang.junqin.chaexpress.ExpressApplication;
import wang.junqin.chaexpress.R;
import wang.junqin.chaexpress.model.bean.ExpressInfoBean;

/**
 * Created by KN on 2017/6/5.
 */

public class ExpressInfoDetailsAdapter  extends RecyclerView.Adapter<ExpressInfoDetailsAdapter.ExpInfoDatailsViewHolder>{

    List<ExpressInfoBean.Data> list;
    public ExpressInfoDetailsAdapter(List<ExpressInfoBean.Data> list){
        this.list = list;
    }

    @Override
    public ExpInfoDatailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(ExpressApplication.getContext()).inflate(R.layout.item_exp_info_details_data,parent,false);
        return new ExpInfoDatailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExpInfoDatailsViewHolder holder, int position) {
        holder.date.setText(list.get(position).getTime());
        holder.info.setText(list.get(position).getContext());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<ExpressInfoBean.Data> data){
        list.clear();
        list.addAll(data);
    }

    public void clearAll(){
        list.clear();
    }

    class ExpInfoDatailsViewHolder extends RecyclerView.ViewHolder{

        TextView date,info;
        ImageView statusImg;
        public ExpInfoDatailsViewHolder(View itemView) {
            super(itemView);
            statusImg = (ImageView) itemView.findViewById(R.id.item_exp_info_details_img);
            date = (TextView) itemView.findViewById(R.id.item_exp_info_details_time);
            info = (TextView) itemView.findViewById(R.id.item_exp_info_details_info);
        }
    }

}
