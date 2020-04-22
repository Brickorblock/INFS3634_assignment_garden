package com.example.infs3634_assignment_garden.entities.Video;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VideoService {
//Need to be able to query search using the chapter name.
//The max results needs to be considered so that we can limit the search to 10 videos.
//Other parameters are required by the YouTube api in order to work.
    @GET("search?")
    Call<VideoResponse> getVideo(@Query("part") String part, @Query("q") String q,
                                 @Query("type") String type, @Query("videoCaption") String videoCaption,
                                 @Query("maxResults") String maxResults, @Query("key") String key);

}