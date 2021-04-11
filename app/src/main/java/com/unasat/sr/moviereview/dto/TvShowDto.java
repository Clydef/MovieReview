package com.unasat.sr.moviereview.dto;

public class TvShowDto {
    private String image;
    private String name;
    private String genres;
    private String rating;
    private String average;
    private String summary;
    //private String show;

    public TvShowDto(String name, String summary) {
        this.name = name;
        this.summary = summary;
    }

    public TvShowDto() {
    }

    public TvShowDto(String show) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getRating() { return rating; }

    public void setRating(String rating) { this.rating = rating; }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

//    public String getShow() {
//        return show;
//    }
//
//    public void setShow(String show) {
//        this.show = show;
//    }

    @Override
    public String toString() {
        return "TV Show:" + "\n" +
                "Name: " + name + "\n" +
                "Genres: " + genres + "\n" +
                "Rating: " + rating + "\n" +
                "Summary: " + summary + "\n" +
                "--------------------------------" + "\n";
    }
}
