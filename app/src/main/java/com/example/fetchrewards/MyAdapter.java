package com.example.fetchrewards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    LayoutInflater inflater;
    List<UserInfo> userInfoList;



    public MyAdapter(Context ctx, List<UserInfo> info){
        this.inflater = LayoutInflater.from(ctx);
        this.userInfoList = info;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(userInfoList.get(position).getId());
        holder.myText2.setText(userInfoList.get(position).getListId());
        holder.myText3.setText(userInfoList.get(position).getName());



    }

    @Override
    public int getItemCount() {
        return userInfoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView myText1, myText2, myText3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            myText1 = itemView.findViewById(R.id.tv_view1);
            myText2 = itemView.findViewById(R.id.tv_view2);
            myText3 = itemView.findViewById(R.id.tv_view3);

        }
    }
}
