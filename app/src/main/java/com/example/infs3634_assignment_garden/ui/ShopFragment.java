package com.example.infs3634_assignment_garden.ui;

import android.os.BadParcelableException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634_assignment_garden.MainActivity;
import com.example.infs3634_assignment_garden.ShopAdapter;
import com.example.infs3634_assignment_garden.R;


import com.example.infs3634_assignment_garden.entities.Garden;
import com.example.infs3634_assignment_garden.entities.Plant;
import com.example.infs3634_assignment_garden.entities.ShopItem;

import java.util.ArrayList;

public class ShopFragment extends Fragment implements ShopAdapter.ClickListener {
    //todo
    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    private ArrayList<ShopItem> shopList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        shopList = ShopItem.getShopItems();
        Log.d("ShopFragment", "CurrentList: " +shopList);

        View root = inflater.inflate(R.layout.fragment_shop, container, false);

        myRecyclerView = root.findViewById(R.id.shopRecycler);

        myAdapter = new ShopAdapter(this.shopList, this);

        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        myRecyclerView.setAdapter(myAdapter);

        return root;
    }


    @Override
    public Boolean purchasePlant(Plant purchaseItem, int cost) {
        Boolean purchaseSuccessful = false;
            if (Garden.getPlants().size() < Garden.MAX_PLANTS) {
                if (Garden.getCoins() >= cost) {
                    Garden.deductCoins(cost);
                    Garden.addPlant(purchaseItem);

                    //navigate back to Garden screen
                    MainActivity.navController.popBackStack();

                    purchaseSuccessful = true;
                } else {
                    //purchase failed
                    String msg = "You don't have enough Coins!";
                    Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                }

            } else {
                //purchase failed
                String msg = "Your Garden is too full!";
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        return purchaseSuccessful;
    }
}
