package com.example.infs3634_assignment_garden;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.infs3634_assignment_garden.entities.Video.Item;
import com.example.infs3634_assignment_garden.entities.Video.VideoDetails;
import com.example.infs3634_assignment_garden.entities.Video.VideoResponse;
import com.example.infs3634_assignment_garden.entities.Video.VideoService;
import com.example.infs3634_assignment_garden.ui.ChapterFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class VideosFragment extends Fragment implements VideoAdapter.LaunchListener {
private static List<VideoDetails> FinalVideoList;

    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter myAdapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_videos, container, false);
        myRecyclerView = root.findViewById(R.id.videorecyler);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        Bundle bundle = getArguments();
//        String chapter = null;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//            chapter = bundle.getString(ChapterFragment.KEY_ChapterName);
//        }
//
//        Retrofit.Builder builder = new Retrofit.Builder()
//                .baseUrl("https://www.googleapis.com/youtube/v3/")
//                .addConverterFactory(GsonConverterFactory.create());
//        Retrofit retrofit = builder.build();
//        VideoService service = retrofit.create(VideoService.class);
//        Call<VideoLoreResponse> call = service.getVideo("snippet", chapter, "video", "closedCaption", "10", "AIzaSyDxidLcL8C1mzLznTTqmniCrGm6yT3Ymu4");
//        call.enqueue(new Callback<VideoLoreResponse>() {
//            @Override
//            public void onResponse(Call<VideoLoreResponse> call, Response<VideoLoreResponse> response) {
//
//                VideoLoreResponse videos = response.body();
//                Log.d("Main Activity", "Video Response: " + videos);
//                List<Item> videoitems = videos.getItems();
//                Log.d("Main Activity", "Video Items: " + videoitems);
//                List<VideoDetails> VideoList = new ArrayList<VideoDetails>();
//                for(int i = 0; i < videoitems.size(); i++) {
//
//                    String videoid = videoitems.get(i).getId().getVideoId();
//                    String videotitle = videoitems.get(i).getSnippet().getTitle();
//                    String videodescription = videoitems.get(i).getSnippet().getDescription();
//                    String videochannel = videoitems.get(i).getSnippet().getChannelTitle();
//
//                    VideoDetails finalvideo = new VideoDetails(videoid, videotitle, videodescription, videochannel);
//                    Log.d("Main Activity", "Final Video: " + finalvideo);
//                    VideoList.add(finalvideo);
//                }
//                Log.d("Main Activity", "VideoList: " + VideoList);
//                setVidoes(VideoList);
//            }
//
//            @Override
//            public void onFailure(Call<VideoLoreResponse> call, Throwable t) {
//                Log.d("Main Activity", "Failed to get Videos");
//            }
//        });

//Creating a network assignment object so that we can run the API call
        new NetworkAssignment().execute();
        return root;
    }
//Async Task for API call.
    public class NetworkAssignment extends AsyncTask<Void, Integer, List<VideoDetails>> {


        @Override
        protected List<VideoDetails> doInBackground(Void... voids) {
//Grabbing the chapter name from the chapter fragment
            Bundle bundle = getArguments();
            String chapter = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                chapter = bundle.getString(ChapterFragment.KEY_ChapterName);
            }

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://www.googleapis.com/youtube/v3/")
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            VideoService service = retrofit.create(VideoService.class);
            //When calling the API we have to pass in video details.
            //In particular we need to pass in the search query of the chapter name dynamically which we do through using a bundle.
            //We also set the max results to 10 as this is the max amount of videos we want to show on this page.
            Call<VideoResponse> call = service.getVideo("snippet", chapter, "video", "closedCaption", "10", "AIzaSyDxidLcL8C1mzLznTTqmniCrGm6yT3Ymu4");
            List<VideoDetails> VideoList = new ArrayList<VideoDetails>();
        try {
            Response<VideoResponse> response = call.execute();
            //After API call we get the class body and create a video response class from it.
            VideoResponse videos = response.body();
            Log.d("Main Activity", "Video Response: " + videos);
            //As we only care about the items of this response, we create a Video Item list of all items in the search (10).
            List<Item> videoitems = videos.getItems();
            Log.d("Main Activity", "Video Items: " + videoitems);

            //This loop goes through every item and gets the appropriate video details from it.
            //It then creates a video detail object for these attributes and adds this to the video details class we created earlier.
            for(int i = 0; i < videoitems.size(); i++) {

                String videoid = videoitems.get(i).getId().getVideoId();
                String videotitle = videoitems.get(i).getSnippet().getTitle();
                String videodescription = videoitems.get(i).getSnippet().getDescription();
                String videochannel = videoitems.get(i).getSnippet().getChannelTitle();

                VideoDetails finalvideo = new VideoDetails(videoid, videotitle, videodescription, videochannel);
                Log.d("Main Activity", "Final Video: " + finalvideo);
                VideoList.add(finalvideo);
            }
            Log.d("Main Activity", "VideoList: " + VideoList);

        } catch (Exception e) {
            e.printStackTrace();
        }

            return VideoList;
        }
//this method sets the adapter with the final video list so that the UI changes dynamically to our API call data.
        @Override
        protected void onPostExecute (List<VideoDetails> VideoList) {
            super.onPostExecute(VideoList);

            if(VideoList != null) {
                setVidoes(VideoList);
            } else {


            }

        }

    }

//This method sets the given list into the adapter and sets the adapter to the recycler view.
    public void setVidoes(List<VideoDetails> newVideos){

        FinalVideoList = newVideos;
        myAdapter = new VideoAdapter(FinalVideoList, this);
        myRecyclerView.setAdapter(myAdapter);
        Log.d("Main Activity", "FinalVideoList " + FinalVideoList);

    }

//Whenever a video is clicked the appropriate details are sent to the YouTube Fragment so that they can display the video itself along with its details.
    @Override
    public void launch(int position) {

        VideoDetails targetVideo = videoSearch(position);
        String title = targetVideo.getTitle();
        //This video id is what is used to get the specific video from YouTube to be displayed in the YouTube player.
        String videoId = targetVideo.getVideoId();
        String description = targetVideo.getDescription();
        String channeltitle = targetVideo.getChanneltitle();

        Bundle intentBundle = new Bundle();
        intentBundle.putString("title", title);
        intentBundle.putString("videoId", videoId);
        intentBundle.putString("description", description);
        intentBundle.putString("channeltitle", channeltitle);
        MainActivity.navController.navigate(R.id.action_videosFragment_to_youtubeFragment, intentBundle);

    }

//This method gets the chosen video as an object based on the clicked video position.
    public static VideoDetails videoSearch(int position) {

        VideoDetails targetVideo = FinalVideoList.get(position);
        return targetVideo;
    }
}

