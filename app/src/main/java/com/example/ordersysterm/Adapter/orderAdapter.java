package com.example.ordersysterm.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordersysterm.R;

import java.util.List;

public class orderAdapter extends RecyclerView.Adapter<orderAdapter.ViewHolder> {

    Context context;
    List<String> dataList;
    SharedPreferences.Editor editor;

    public orderAdapter(Context context,List<String> dataList){
        this.dataList=dataList;
        this.context=context;
        editor=context.getSharedPreferences("order_data",Context.MODE_PRIVATE).edit();
    }

    @NonNull
    @Override
    public orderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        ViewHolder holder=new ViewHolder(view);
        holder.deleteIb.setOnClickListener(v -> {
            int position=holder.getAdapterPosition();
            dataList.remove(position);
            notifyItemRemoved(position);
            //SP中删除记录
            editor.remove(""+position);
            editor.apply();
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull orderAdapter.ViewHolder holder, int position) {
        holder.orderText.setText(dataList.get(position));
        //头图绑定
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView orderText;
        public ImageView headerIv;
        public ImageButton deleteIb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderText = itemView.findViewById(R.id.item_order_tv);
            headerIv = itemView.findViewById(R.id.item_order_iv);
            deleteIb = itemView.findViewById(R.id.item_order_delete_ib);
        }
    }
}

