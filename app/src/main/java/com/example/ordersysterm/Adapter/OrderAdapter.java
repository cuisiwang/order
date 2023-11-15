package com.example.ordersysterm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordersysterm.R;
import com.example.ordersysterm.orderDatabase.orderDao;
import com.example.ordersysterm.orderDatabase.orderData;
import com.example.ordersysterm.orderDatabase.orderDatabase;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    Context context;
    List<orderData> dataList;
    orderDao orderDao;
    public OrderAdapter(Context context, List<orderData> dataList){
        this.dataList=dataList;
        this.context=context;
        this.orderDao= orderDatabase.Companion.getDatabase(context).orderDao();
    }


    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        ViewHolder holder=new ViewHolder(view);
        holder.deleteIb.setOnClickListener(v -> {
            int position=holder.getAdapterPosition();
            if(position>=0){
                orderData tmp=dataList.get(position);
                //room中删除记录
                new Thread(()->{
                    orderDao.deleteOrder(tmp);
                }).start();
                dataList.remove(position);
                notifyItemRemoved(position);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        holder.orderText.setText(dataList.get(position).getName());
        holder.timeTv.setText(dataList.get(position).getTime());
        String tmp="¥"+dataList.get(position).getPrice();
        holder.priceTv.setText(tmp);
        tmp="x"+dataList.get(position).getNumber();
        holder.numberTv.setText(tmp);
        holder.dscTv.setText(dataList.get(position).getDescription());
        //头图绑定
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView orderText;
        private final ImageView headerIv;
        private final ImageButton deleteIb;
        private final TextView timeTv;
        private final TextView dscTv;
        private final TextView priceTv;
        private final TextView numberTv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderText = itemView.findViewById(R.id.item_order_tv);
            headerIv = itemView.findViewById(R.id.item_order_iv);
            deleteIb = itemView.findViewById(R.id.item_order_delete_ib);
            timeTv = itemView.findViewById(R.id.item_order_tv_time);
            dscTv = itemView.findViewById(R.id.item_order_tv_dsc);
            priceTv = itemView.findViewById(R.id.item_order_tv_price);
            numberTv = itemView.findViewById(R.id.item_order_tv_number);
        }
    }
}

