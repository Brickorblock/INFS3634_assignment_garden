package com.example.infs3634_assignment_garden;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.infs3634_assignment_garden.ui.GardenFragment;
import com.example.infs3634_assignment_garden.ui.ShopFragment;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        ShopFragment fragment = new ShopFragment();

        FragmentTransaction initTransaction = getSupportFragmentManager().beginTransaction();

        initTransaction.add(R.id.fragmentContainer, fragment);
        initTransaction.commit();

        Intent intent = getIntent();
        String message = intent.getStringExtra(GardenFragment.key1);

        Bundle intentBundle = new Bundle();
        intentBundle.putString(GardenFragment.key1, message);
        fragment.setArguments(intentBundle);


    }
}
