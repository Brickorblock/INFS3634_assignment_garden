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
import android.widget.TextView;

import com.example.infs3634_assignment_garden.entities.Video.Item;
import com.example.infs3634_assignment_garden.entities.Video.VideoDetails;
import com.example.infs3634_assignment_garden.entities.Video.VideoLoreResponse;
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

//Establishing global variables so that they can be dynamically set and called upon later.

    private static List<VideoDetails> FinalVideoList;
    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private TextView loading;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_videos, container, false);

        //Setting the recyclerview which will be called and have its adapter set later.
        myRecyclerView = root.findViewById(R.id.videorecyler);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loading = root.findViewById(R.id.loadingText);
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
//An Async Task is called in order to do the YouTube API call to get the list of YouTube videos to display
        new NetworkAssignment().execute();
        return root;
    }

    public class NetworkAssignment extends AsyncTask<Void, Integer, List<VideoDetails>> {


        @Override
        protected List<VideoDetails> doInBackground(Void... voids) {
        //Grabbing the bundle of chapter name from the chapter Fragment, this is so we can dynamically pass it into the retrofit call
            Bundle bundle = getArguments();
            String chapter = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                chapter = bundle.getString(ChapterFragment.KEY_ChapterName);
            }

            //Using retrofit to call the google api and get a JSON text file of a YouTube Search of 10 Videos based on the chapter name.
            //This JSON text is then converted to a response Java class using the GsonConverterFactory.
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://www.googleapis.com/youtube/v3/")
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            VideoService service = retrofit.create(VideoService.class);
            //Physically calling the video service interface to grab the search data from the google API.
            // The parameters allow us to control the information that is grabbed from the internet.
            //e.g. the search query of the chapter name, the number of max results which we need to be 10, and the public API key we will use to access YouTube data.
            Call<VideoLoreResponse> call = service.getVideo("snippet", chapter, "video", "closedCaption", "10", "AIzaSyDxidLcL8C1mzLznTTqmniCrGm6yT3Ymu4");
            //Establishing a Video Details Arraylist which will be filled in during the execution of the call.
            List<VideoDetails> VideoList = new ArrayList<VideoDetails>();
            try {
                Response<VideoLoreResponse> response = call.execute();
                VideoLoreResponse videos = response.body();
                Log.d("Main Activity", "Video Response: " + videos);
                //Grabbing the item data from the response. Items holds the useful information about the videos that we need.
                List<Item> videoitems = videos.getItems();
                Log.d("Main Activity", "Video Items: " + videoitems);
                for (int i = 0; i < videoitems.size(); i++) {
//This for loop goes through every item on the list (10 due to our query parameter) and grabs the video id, video title, video description and video channel.
                    //This information will be the attributes passed into the adapter to display on the recyclerview and YouTube Fragment.
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

        @Override
        protected void onPostExecute(List<VideoDetails> VideoList) {

            //This method calls the set Videos method to set the adapter to the Video Details ArrayList
            super.onPostExecute(VideoList);

            if (VideoList != null) {
                setVideos(VideoList);
            } else {


            }

        }

    }


    public void setVideos(List<VideoDetails> newVideos) {
//This method sets the adapter and recyclerview to use the Video Details ArrayList which was just filled in during the Async Task execution before hand.
        FinalVideoList = newVideos;
        myAdapter = new VideoAdapter(FinalVideoList, this);
        myRecyclerView.setAdapter(myAdapter);
        //This code makes the loading screen insivible as soon as the adapter is set.
        loading.setVisibility(View.INVISIBLE);
        Log.d("Main Activity", "FinalVideoList " + FinalVideoList);

    }


    @Override
    public void launch(int position) {
//the video search method is called to get the clicked video as a class of Video Details
        //Then the video attributes such as title, ID, description and channel title are all obtained and sent to the YouTube Fragment.
        VideoDetails targetVideo = videoSearch(position);
        String title = targetVideo.getTitle();
        String videoId = targetVideo.getVideoId();
        String description = targetVideo.getDescription();
        String channeltitle = targetVideo.getChanneltitle();

        Bundle intentBundle = new Bundle();
        intentBundle.putString("title", title);
        intentBundle.putString("videoId", videoId);
        intentBundle.putString("description", description);
        intentBundle.putString("channeltitle", channeltitle);
        //The click changes the navigation of the navigation controller to YouTube Fragment through using an action.
        //This allows us to essentially have the ability to access multiple fragments within one navigation view i.e. dynamic fragment switching.
        MainActivity.navController.navigate(R.id.action_videosFragment_to_youtubeFragment, intentBundle);

    }


    public static VideoDetails videoSearch(int position) {
//Whenever a video is clicked, the position is used to determine which video was actually clicked.
        VideoDetails targetVideo = FinalVideoList.get(position);
        return targetVideo;
    }
}

