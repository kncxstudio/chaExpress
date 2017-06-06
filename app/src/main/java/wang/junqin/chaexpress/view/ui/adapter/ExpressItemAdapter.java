package wang.junqin.chaexpress.view.ui.adapter;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import wang.junqin.chaexpress.utils.DAO.ExpressEntity;
import wang.junqin.chaexpress.ExpressApplication;
import wang.junqin.chaexpress.R;
import wang.junqin.chaexpress.model.bean.ExpressInfoBean;

/**
 * Created by KN on 2017/6/2.
 */

public class ExpressItemAdapter extends RecyclerView.Adapter<ExpressItemAdapter.ExpressViewHolder> implements View.OnClickListener,View.OnLongClickListener{

    private RecyclerViewItemClickListener listener;
    private List<ExpressEntity> expressList;
    public ExpressItemAdapter(List<ExpressEntity> list){
        this.expressList = list;
    }


    @Override
    public ExpressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ExpressApplication.getContext()).inflate(R.layout.item_express_base,parent,false);
        return new ExpressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExpressViewHolder holder, final int position) {
        ExpressEntity entity = expressList.get(position);
        if (entity.getRemark() == null) {
            holder.remark.setText(entity.getExpNum());
        }else {
            holder.remark.setText(entity.getRemark());
        }
        if (entity.getStatus().equals("is_checked")){
            holder.status.setText("已签收");
            holder.status.setTextColor(Color.parseColor("#31d452"));
        }

        List<ExpressInfoBean.Data> infos = new Gson().fromJson(entity.getExpInfo(),new TypeToken<List<ExpressInfoBean.Data>>(){}.getType());


        if (infos != null && infos.size() > 0) {
            holder.latestInfo.setText(infos.get(0).getContext());
            holder.refreshDate.setText(infos.get(0).getFtime());
        }else {
            holder.refreshDate.setText("暂无物流信息");
        }
        holder.cardView.setTag(expressList.get(position));
        holder.cardView.setOnClickListener(this);
        holder.cardView.setOnLongClickListener(this);

    }

    @Override
    public int getItemCount() {
        return expressList.size();
    }

    public void removeAll(){
        expressList.clear();
    }

    public void addAll(List<ExpressEntity> list){
        expressList.addAll(list);
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(v);
    }

    @Override
    public boolean onLongClick(View v) {
        listener.onItemLongClick(v);
        return false;
    }


    class ExpressViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView remark,refreshDate,latestInfo,status;
        CardView cardView;
        public ExpressViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_item_com);
            remark = (TextView) itemView.findViewById(R.id.tv_item_remark);
            refreshDate = (TextView) itemView.findViewById(R.id.tv_item_date);
            latestInfo = (TextView) itemView.findViewById(R.id.tv_item_latest);
            status = (TextView) itemView.findViewById(R.id.tv_item_status);
            cardView = (CardView) itemView.findViewById(R.id.item_cardview);
        }
    }

    public void setOnItemClickListener(RecyclerViewItemClickListener listener){
        this.listener = listener;
    }

}
