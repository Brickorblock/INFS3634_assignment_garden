package com.example.infs3634_assignment_garden.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634_assignment_garden.MainActivity;
import com.example.infs3634_assignment_garden.Plant;
import com.example.infs3634_assignment_garden.PlantAdapter;
import com.example.infs3634_assignment_garden.R;

import java.util.ArrayList;

public class GardenFragment extends Fragment implements PlantAdapter.LaunchListener{

    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    private ArrayList<Plant> currList;

    public static String key1 = "GardenFragment_positionBundle";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //specify fragment root
        View root = inflater.inflate(R.layout.fragment_garden, container, false);

        //create recyclerView
        RecyclerView myRecyclerView = root.findViewById(R.id.plantRecycler);
        myRecyclerView.setHasFixedSize(true);

        myLayoutManager = new GridLayoutManager(getActivity(), 5);
        myRecyclerView.setLayoutManager(myLayoutManager);

        //create adapter (using Restaurant list as dataset)
        ArrayList<Plant> plants = MainActivity.user.getPlants();
        myAdapter = new PlantAdapter(plants, this);
        myRecyclerView.setAdapter(myAdapter);

        //populate ambience & coins fields
        TextView ambienceLvl = root.findViewById(R.id.lvlText);
        ProgressBar ambienceBar = root.findViewById(R.id.ambienceBar);
        TextView coinText = root.findViewById(R.id.coinText);

        ambienceLvl.setText("Lvl " + Integer.toString(MainActivity.user.getAmbienceLvl()));
        ambienceBar.setProgress((int) MainActivity.user.getAmbienceProgress());
        coinText.setText(Integer.toString(MainActivity.user.getCoins()));

        return root;
    }

    @Override
    public void launch(int position) {
        // binds PlantDetailFragment to constraint layout in GardenFragment
        PlantDetailFragment fragment = new PlantDetailFragment();

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (fragment == null) {
            Log.d("TAG", "launch: transaction.add()");
            transaction.add(R.id.plantDetailContainer, fragment);
        } else {
            Log.d("TAG", "launch: transaction.replace()");
            transaction.replace(R.id.plantDetailContainer, fragment);
        }
        transaction.commit();

        //pass bundle containing position of item clicked
        Bundle positionBundle = new Bundle();
        positionBundle.putInt(key1, position);
        fragment.setArguments(positionBundle);

    }
}
