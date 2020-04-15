package com.example.infs3634_assignment_garden;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634_assignment_garden.entities.Garden;
import com.example.infs3634_assignment_garden.entities.Plant;
import com.example.infs3634_assignment_garden.entities.ShopItem;

import java.util.ArrayList;

public class  ShopAdapter extends RecyclerView.Adapter<ShopAdapter.MyViewHolder> {
    private ArrayList<ShopItem> dataset;
    private ClickListener mClickListener;

    ShopItem shopItem;
    Plant plant;

    public ShopAdapter(ArrayList<ShopItem> dataset, ClickListener clickListener) {
        this.dataset = dataset;
        Log.d("TAG", "ShopAdapter: " + dataset.toString());
        this.mClickListener = clickListener;
    }

    @NonNull
    @Override
    public ShopAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shop_itemview, parent, false);

        ShopAdapter.MyViewHolder vh = new ShopAdapter.MyViewHolder(v, mClickListener);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        shopItem = dataset.get(position);
        plant = shopItem.getPlant();

        holder.topicText.setText(plant.getTopic());
        holder.plantNameText.setText(plant.getName());
        Log.d("TAG", "onBindViewHolder: plant toString " + plant.toString());
        holder.plantImage.setImageResource(plant.getPlantImage());
        holder.valueText.setText(Integer.toString(shopItem.getCost()));
        holder.requiredLvlText.setText(Integer.toString(shopItem.getLevelRequirement()));


        int requiredlvl = shopItem.getLevelRequirement();
        int userLvl = Garden.getAmbienceLvl();

        if(userLvl >= requiredlvl){
            //user has met required level
            holder.requiredLvlConstraint.setVisibility(View.INVISIBLE);
        } else {
            //user has NOT met required level
            holder.buyButton.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView plantImage;
        public TextView plantNameText;
        public TextView topicText;
        public TextView valueText;

        ClickListener clickListener;
        //@Justin - buyButton & requiredLvlConstraint are the two elements that we will
        // swap their visibilities depending on if the user is high enough level
        // both are visible by default; u will have to implement the visibility switcher
        public Button buyButton;
        public ConstraintLayout requiredLvlConstraint;
        public TextView requiredLvlText;

        public MyViewHolder(@NonNull View itemView, ClickListener clickListener) {
            super(itemView);

            this.clickListener = clickListener;
            this.plantImage = itemView.findViewById(R.id.plantImage);
            this.plantNameText = itemView.findViewById(R.id.plantnameText);
            this.topicText = itemView.findViewById(R.id.topicText);
            this.valueText = itemView.findViewById(R.id.valueText);
            this.requiredLvlText = itemView.findViewById(R.id.requiredLvlText);
            this.buyButton = itemView.findViewById(R.id.buyButton);
            this.requiredLvlConstraint = itemView.findViewById(R.id.requiredLvlConstraint);

            buyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShopItem currItem = dataset.get(getAdapterPosition());

                    Log.d("TAG", "onClick: position = " + getAdapterPosition() + "| shopItem = " + currItem.toString());
                    Log.d("TAG", "onClick: plant = " + currItem.getPlant().toString());
                    mClickListener.purchasePlant(currItem.getPlant(), currItem.getCost());
                }
            });
        }
    }

    public interface ClickListener {
        Boolean purchasePlant(Plant purchaseItem, int cost);
    }
}
