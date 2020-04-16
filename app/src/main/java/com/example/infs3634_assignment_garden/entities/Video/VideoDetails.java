package com.example.infs3634_assignment_garden.entities.Video;

public class VideoDetails {
    public String videoId, title, description, channeltitle;

    public VideoDetails(String videoId, String title, String description, String channeltitle) {
        this.videoId = videoId;
        this.title = title;
        this.description = description;
        this.channeltitle = channeltitle;
    }

    public VideoDetails(){}

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChanneltitle() {
        return channeltitle;
    }

    public void setChanneltitle(String channeltitle) {
        this.channeltitle = channeltitle;
    }

    @Override
    public String toString() {
        return "VideoDetails{" +
                "videoId='" + videoId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
