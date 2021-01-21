package com.favorites.service;

import java.util.*;


import com.favorites.model.Media;
import com.favorites.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class FavouritesService {


    @Autowired
    public FavouritesService() {
    }

    public void addToFavourites(String mediaId) {
        String urlUser = "http://localhost:8092/currentUser";
        RestTemplate templateUser = new RestTemplate();
        User currentUser = templateUser.getForObject(urlUser, User.class);

        List<String> favouriteMovies = currentUser.getfavouriteMovies();
        if(favouriteMovies == null) {
            favouriteMovies = new LinkedList<>();
        }
        if(favouriteMovies.contains(mediaId)) {
            throw new RuntimeException("Media id already exists!");
        }

        favouriteMovies.add(mediaId);
        currentUser.setfavouriteMovies(favouriteMovies);
        String urlUpdateUser = "http://localhost:8092/updateUser";
        RestTemplate updateUser = new RestTemplate();
        updateUser.put(urlUpdateUser, currentUser, User.class);
    }

    public void removeFavourite(String movieId) {
        String urlUser = "http://localhost:8092/currentUser";
        RestTemplate templateUser = new RestTemplate();
        User currentUser = templateUser.getForObject(urlUser, User.class);

        List<String> userFavorites = currentUser.getfavouriteMovies();
        if(userFavorites == null) {
            return;
        }
        if(userFavorites.remove(movieId)) {
            currentUser.setfavouriteMovies(userFavorites);
            String urlUpdateUser = "http://localhost:8092/updateUser";
            RestTemplate updateUser = new RestTemplate();
            updateUser.put(urlUpdateUser, currentUser, User.class);
        }
    }

    public List<Media> getFavourites() {

        String urlUser = "http://localhost:8092/currentUser";
        RestTemplate templateUser = new RestTemplate();

        String urlMedia = "http://localhost:8095/GetMedia?title={title}";
        RestTemplate templateMedia = new RestTemplate();

        User currentUser = templateUser.getForObject(urlUser, User.class);
        List<String> userFavorites = currentUser.getfavouriteMovies();

        if(userFavorites == null || userFavorites.isEmpty()) {
            return new LinkedList<>();
        }

        List<Media> favouriteMovies = new LinkedList<>();
        for(String media : userFavorites) {
            Media mediaResponse = templateMedia.getForObject(urlMedia, Media.class, media);
            favouriteMovies.add(mediaResponse);
        }
        return favouriteMovies;
    }

}