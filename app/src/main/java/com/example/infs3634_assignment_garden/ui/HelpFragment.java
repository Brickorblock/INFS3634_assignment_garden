package com.example.infs3634_assignment_garden.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.infs3634_assignment_garden.R;

public class HelpFragment extends Fragment {
    private ViewFlipper viewFlipper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_help, container, false);

        viewFlipper = root.findViewById(R.id.viewFlipper);
        ImageView nextButton = root.findViewById(R.id.nextButton);
        ImageView prevButton = root.findViewById(R.id.prevButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextView();
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousView();
            }
        });

        return root;
    }

    public void previousView(){
        viewFlipper.showPrevious();
    }

    public void nextView(){
        viewFlipper.showNext();
    }
}
