package com.example.infs3634_assignment_garden.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634_assignment_garden.ShopAdapter;
import com.example.infs3634_assignment_garden.R;


import com.example.infs3634_assignment_garden.entities.ShopItem;

import java.util.ArrayList;

public class ShopFragment extends Fragment implements ShopAdapter.LaunchListener {
    //todo
    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    private ArrayList<ShopItem> shopList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ArrayList<ShopItem> currList = ShopItem.createShopItem(shopList);
        Log.d("ShopFragment", "CurrentList: " +currList);

        View root = inflater.inflate(R.layout.fragment_shop, container, false);

        myRecyclerView = root.findViewById(R.id.shopRecycler);

        myAdapter = new ShopAdapter(shopList, this);

        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        myRecyclerView.setAdapter(myAdapter);

        Log.d("TAG", "onCreateView: prev screen is ");

        return root;
    }

    @Override
    public void launch(int position) {
        //todo - this method is called when an item is clicked


    }


}
