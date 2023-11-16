package com.example.ordersysterm.Adapter;

import android.content.Context;
import android.util.Log;
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
    TextView totalPriceTv;

    public CartAdapter(Context context, List<CartItem> dataList, TextView totalPriceTv){
        this.dataList=dataList;
        this.context=context;
        this.totalPriceTv=totalPriceTv;
    }


    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        ViewHolder holder=new ViewHolder(view);

        //涉及到RV里删除的按钮一定要放在这里。。放到onBindViewHolder里的话删除后position会出问题。。
        holder.minus_btn.setOnClickListener(v -> {
            int position=holder.getAdapterPosition();
            CartItem itm = dataList.get(position);
            if(itm.getNumber()==1){
                dataList.remove(position);
                notifyItemRemoved(position);
            }else {
                int num=itm.getNumber()-1;
                int singlePrice=itm.getPrice()/(num+1);
                dataList.set(position,
                        new CartItem(itm.getName(),itm.getDescription(),singlePrice*num,num));
                notifyItemChanged(position);
            }
            int totalPrice=0;
            for(CartItem item : dataList) totalPrice+=item.getPrice();
            totalPriceTv.setText("总价：¥"+totalPrice);
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cartName.setText(dataList.get(position).getName());
        String tmp="¥"+dataList.get(position).getPrice();
        holder.priceTv.setText(tmp);
        holder.cartDsc.setText(dataList.get(position).getDescription());
        tmp="x"+dataList.get(position).getNumber();
        holder.cartNumber.setText(tmp);
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
