package com.pmdm.saj.definitiveconcesionario.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pmdm.saj.definitiveconcesionario.R;
import com.pmdm.saj.definitiveconcesionario.activity.CarActivity;
import com.pmdm.saj.definitiveconcesionario.entity.Car;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder>{
    ArrayList<Car> cars;
    Context context;

    public CarAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = cars.get(position);
        String description = car.description.substring(0, Math.min(car.description.length(), 150)) + "[...]",
                price = car.price + "€",
                power = car.power + "cv",
                doors = car.doors + " puertas",
                kms = car.kms + "km";

        holder.car = car;
        holder.tvAddTitle.setText(car.title);
        holder.tvAddLocation.setText(car.location);
        holder.tvAddDescription.setText(description);
        holder.tvAddPrice.setText(price);
        holder.tvAddPower.setText(power);
        holder.tvAddDoors.setText(doors);
        holder.tvAddFuel.setText(car.fuel);
        holder.tvAddKms.setText(kms);
        holder.tvAddYear.setText(String.valueOf(car.year));
        holder.tvAddShift.setText(car.shift);
        holder.tvAddColor.setText(car.color);
        String image = car.images[0].trim();
        Picasso.get().load(image).into(holder.ivCarPreview);
    }

    @Override
    public int getItemCount() {
        if (cars == null){
            return 0;
        }
        return cars.size();
    }

    public void setList(ArrayList<Car> cars){
        this.cars = cars;
    }


    class CarViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivCarPreview;
        public TextView tvAddTitle, tvAddLocation, tvAddDescription, tvAddPrice, tvAddPower, tvAddDoors,
                tvAddFuel, tvAddKms, tvAddYear, tvAddShift, tvAddColor;
        Car car;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCarPreview = itemView.findViewById(R.id.ivCarPreview);
            tvAddTitle = itemView.findViewById(R.id.tvAddTitle);
            tvAddLocation = itemView.findViewById(R.id.tvAddLocation);
            tvAddDescription = itemView.findViewById(R.id.tvAddDescription);
            tvAddPrice = itemView.findViewById(R.id.tvAddPrice);
            tvAddPower = itemView.findViewById(R.id.tvAddPower);
            tvAddDoors = itemView.findViewById(R.id.tvAddDoors);
            tvAddFuel = itemView.findViewById(R.id.tvAddFuel);
            tvAddKms = itemView.findViewById(R.id.tvAddKms);
            tvAddYear = itemView.findViewById(R.id.tvAddYear);
            tvAddShift  = itemView.findViewById(R.id.tvAddShift);
            tvAddColor = itemView.findViewById(R.id.tvAddColor);


            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, CarActivity.class);
                intent.putExtra("car", car);
                context.startActivity(intent);
            });

        }
    }
}
