package com.example.carexample.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.carexample.HelperClass;
import com.example.carexample.R;
import com.example.carexample.data.model.CarData;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder>{


    List<CarData> carDataList;
    Context context;

    public CarAdapter(List<CarData> carDataList, Context context) {
        this.carDataList = carDataList;
        this.context = context;
    }

    public void setCarsList(List<CarData> gitHubRepoList) {
        this.carDataList.addAll(gitHubRepoList);
        notifyDataSetChanged();
        Log.i(HelperClass.TAG, "## CarAdapter ## -- setGitHubList() -- this.listSize() >> "
                + this.carDataList.size()
        );
    }

    @NonNull
    @Override
    public CarAdapter.CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.car_list_item, parent, false);
        return new CarViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.CarViewHolder holder, int position) {
        CarData carData = carDataList.get(position);
        holder.tvBrandName.setText(carData.getBrand());
        holder.tvItUsed.setText(Boolean.toString(carData.isUsed()));
        if (carData.getConstractionYear() != null){
            holder.tvYear.setText(carData.getConstractionYear());
        }
        if (carData.getConstractionYear() != null){
            Glide.with(context).load(carData.getImageUrl()).into(holder.ivCarImage);
        }

    }

    @Override
    public int getItemCount() {
        return carDataList.size();
    }

    static class CarViewHolder extends RecyclerView.ViewHolder{


        TextView tvBrandName;
        TextView tvItUsed;
        TextView tvYear;
        ImageView ivCarImage;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBrandName= itemView.findViewById(R.id.tv_brand_name);
            tvItUsed= itemView.findViewById(R.id.tv_is_used);
            tvYear= itemView.findViewById(R.id.tv_year);
            ivCarImage = itemView.findViewById(R.id.iv_car_image);
        }
    }
}
