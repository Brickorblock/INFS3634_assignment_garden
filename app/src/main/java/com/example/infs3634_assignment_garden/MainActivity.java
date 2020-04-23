package com.example.infs3634_assignment_garden;

import android.os.Bundle;

import com.example.infs3634_assignment_garden.entities.Garden;
import com.example.infs3634_assignment_garden.entities.Question;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static Garden garden;
    public static NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_garden, R.id.navigation_quiz,
                R.id.navigation_learn, R.id.navigation_help,
                R.id.navigation_shop, R.id.questionFragment,
                R.id.resultFragment, R.id.chapterFragment,
                R.id.videosFragment, R.id.youtubeFragment)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        //create Garden class (stores global info about progress, etc.)



        this.garden = new Garden();
        //TODO: remove this temp dev debugging method
        garden.getTempPlants();
        garden.getTempQuizzes();
        garden.setCoins(5000);


    }

    @Override
    public void onBackPressed() {
        navController.popBackStack();

    }
}
