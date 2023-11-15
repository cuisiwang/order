package com.example.ordersysterm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordersysterm.Adapter.Item.CartItem;
import com.example.ordersysterm.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    List<CartItem> dataList;

    public CartAdapter(Context context, List<CartItem> dataList){
        this.dataList=dataList;
        this.context=context;
    }


    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        CartAdapter.ViewHolder holder=new CartAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        holder.cartName.setText(dataList.get(position).getName());
        String tmp="¥"+dataList.get(position).getPrice();
        holder.priceTv.setText(tmp);
        holder.cartDsc.setText(dataList.get(position).getDescription());
        tmp="x"+dataList.get(position).getNumber();
        holder.cartNumber.setText(tmp);

        holder.minus_btn.setOnClickListener(v -> {
            if(dataList.get(position).getNumber()==1){
                dataList.remove(position);
                this.notifyItemRemoved(position);
            }else {
                CartItem itm = dataList.get(position);
                dataList.set(position,
                        new CartItem(itm.getName(),itm.getDescription(),itm.getPrice(),itm.getNumber()-1));
                this.notifyItemChanged(position);
            }
        });
        //头图绑定
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView cartName;
        private final TextView priceTv;
        private final TextView cartDsc;
        private final TextView cartNumber;
        private final ImageButton minus_btn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cartName=itemView.findViewById(R.id.item_cart_tv_name);
            priceTv=itemView.findViewById(R.id.item_cart_tv_price);
            cartDsc=itemView.findViewById(R.id.item_cart_tv_dsc);
            cartNumber=itemView.findViewById(R.id.item_cart_tv_number);
            minus_btn=itemView.findViewById(R.id.item_cart_minus_ib);
        }
    }
}
