package com.favorites.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    public User() {}

    private String username;
    private List<String> favouriteMovies;

    public String getUsername() {
        return username;
    }

    public List<String> getfavouriteMovies() {
        return favouriteMovies;
    }

    public void setfavouriteMovies(List<String> favouriteMovies) {
        this.favouriteMovies = favouriteMovies;
    }
}
