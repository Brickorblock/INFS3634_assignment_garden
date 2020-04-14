package com.example.infs3634_assignment_garden.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infs3634_assignment_garden.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

public class YoutubeFragment extends Fragment implements YouTubePlayer.OnInitializedListener {
TextView VideoTitle;
TextView VideoDescription;
TextView Videourl;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_youtube, container, false);

        Bundle bundle = getArguments();
     //   final String youtubelink = bundle.getString(ChapterFragment.KEY_Youtubelink);

        final String Title = bundle.getString("title");
        final String Description = bundle.getString("description");
        final String url = "https://www.youtube.com/watch?v=" + bundle.getString("videoId");

        VideoTitle = root.findViewById(R.id.videotitle);
        VideoDescription = root.findViewById(R.id.videodescription);
        Videourl = root.findViewById(R.id.videourl);

        VideoTitle.setText(Title);
        VideoDescription.setText(Description);
        Videourl.setText(url);

        //////////////////////////////////////////////////////////////////////////

        YouTubePlayerSupportFragment youtubePlayerFragment = new YouTubePlayerSupportFragment();
        youtubePlayerFragment.initialize("AIzaSyDxidLcL8C1mzLznTTqmniCrGm6yT3Ymu4", this);

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//Google hasn't refactored youtube api library to androidx yet. This means that parsing a youtubeplayersupportfragment as a fragment can invoke an 'error'
//Even if the app is run, the code works without an error regardless of the error shown here.

//todo Need to find a way to get rid of error.
        fragmentTransaction.replace(R.id.frame_fragment, youtubePlayerFragment);

        fragmentTransaction.commit();


        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        if(!b){

            Bundle bundle = getArguments();
            final String youtubelink = bundle.getString("videoId");
            youTubePlayer.cueVideo(youtubelink);
            Log.d("YouTube Fragment","Link: " + youtubelink);


            Log.d("Youtube Fragment: ", "Video Cued Successfully!");
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(getActivity(),"fail",Toast.LENGTH_SHORT).show();
    }
}
