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

import com.example.ordersysterm.Adapter.Item.MenuItem;
import com.example.ordersysterm.R;
import com.example.ordersysterm.orderDatabase.orderData;
import com.example.ordersysterm.orderDatabase.orderDatabase;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    Context context;
    List<MenuItem> dataList;
    List<MenuItem> cartDataList;

    public MenuAdapter(Context context, List<MenuItem> dataList,List<MenuItem> cartDataList){
        this.dataList=dataList;
        this.context=context;
        this.cartDataList=cartDataList;
    }


    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        MenuAdapter.ViewHolder holder=new MenuAdapter.ViewHolder(view);
        holder.add_btn.setOnClickListener(v -> {
            cartDataList.add(dataList.get(holder.getAdapterPosition()));
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder holder, int position) {
        holder.menuName.setText(dataList.get(position).getName());
        holder.priceTv.setText("¥"+dataList.get(position).getPrice());
        holder.menuDsc.setText(dataList.get(position).getDescription());
        //头图绑定
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView menuName;
        private final TextView priceTv;
        private final TextView menuDsc;
        private final ImageButton add_btn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            menuName=itemView.findViewById(R.id.item_menu_tv_name);
            priceTv=itemView.findViewById(R.id.item_menu_tv_price);
            menuDsc=itemView.findViewById(R.id.item_menu_tv_dsc);
            add_btn=itemView.findViewById(R.id.item_menu_add_ib);
        }
    }
}
