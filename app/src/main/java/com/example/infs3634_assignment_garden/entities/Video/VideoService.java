package com.example.infs3634_assignment_garden.entities.Video;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VideoService {

    @GET("search?")
    Call<VideoResponse> getVideo(@Query("part") String part, @Query("q") String q,
                                 @Query("type") String type, @Query("videoCaption") String videoCaption,
                                 @Query("maxResults") String maxResults, @Query("key") String key);

}