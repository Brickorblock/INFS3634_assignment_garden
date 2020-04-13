package com.example.infs3634_assignment_garden;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.infs3634_assignment_garden.entities.VideoDetails;
import com.example.infs3634_assignment_garden.ui.LearnFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class VideosFragment extends Fragment {
ListView listview;
String API_KEY = "AIzaSyDxidLcL8C1mzLznTTqmniCrGm6yT3Ymu4";
ArrayList<VideoDetails> videoDetailsArrayList;
VideoAdapter videoAdapter;
String url = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + "Astronomy" + "&type=video&videoCaption=closedCaption&maxResults=10&key=AIzaSyDxidLcL8C1mzLznTTqmniCrGm6yT3Ymu4";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_videos, container, false);
        // Inflate the layout for this fragment
        listview = root.findViewById(R.id.listview);
        videoDetailsArrayList = new ArrayList<>();

        videoAdapter = new VideoAdapter(getActivity(), videoDetailsArrayList);

        displayVideos();
        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void displayVideos() {

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        Bundle bundle = getArguments();
        String topic = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
             topic = bundle.getString(LearnFragment.KEY_TOPIC);
        }

        String url = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + topic + "&type=video&videoCaption=closedCaption&maxResults=10&key=AIzaSyDxidLcL8C1mzLznTTqmniCrGm6yT3Ymu4";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("items");


                    for(int i = 0; i <jsonArray.length(); i++) {

                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        JSONObject jsonVideoId = jsonObject1.getJSONObject("id");
                        JSONObject jsonObjectSnippet = jsonObject1.getJSONObject("snippet");
                        JSONObject jsonObjectDefault = jsonObjectSnippet.getJSONObject("thumbnails").getJSONObject("medium");

                        String video_id = jsonVideoId.getString("videoId");

                        VideoDetails vd = new VideoDetails();

                        vd.setVideoId(video_id);
                        vd.setTitle(jsonObjectSnippet.getString("title"));
                        vd.setDescription(jsonObjectSnippet.getString("description"));
                        vd.setUrl(jsonObjectDefault.getString("url"));

                        videoDetailsArrayList.add(vd);

                    }

                    listview.setAdapter(videoAdapter);
                    videoAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }

        );


        requestQueue.add(stringRequest);
    }
}
