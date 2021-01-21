package com.watchlist.service;

import java.util.*;


import com.watchlist.model.Media;
import com.watchlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WatchlistService {


    @Autowired
    public WatchlistService() {
    }

    public void addToWatchlist(String mediaId) {
        String urlUser = "http://localhost:8092/currentUser";
        RestTemplate templateUser = new RestTemplate();
        User currentUser = templateUser.getForObject(urlUser, User.class);

        List<String> watchlist = currentUser.getWatchlist();
        if(watchlist == null) {
            watchlist = new LinkedList<>();
        }
        if(watchlist.contains(mediaId)) {
            throw new RuntimeException("Media id already exists!");
        }

        watchlist.add(mediaId);
        currentUser.setWatchlist(watchlist);
        String urlUpdateUser = "http://localhost:8092/updateUser";
        RestTemplate updateUser = new RestTemplate();
        updateUser.put(urlUpdateUser, currentUser, User.class);
    }

    public void removeWatchlist(String movieId) {
        String urlUser = "http://localhost:8092/currentUser";
        RestTemplate templateUser = new RestTemplate();
        User currentUser = templateUser.getForObject(urlUser, User.class);

        List<String> userFavorites = currentUser.getWatchlist();
        if(userFavorites == null) {
            return;
        }
        if(userFavorites.remove(movieId)) {
            currentUser.setWatchlist(userFavorites);
            String urlUpdateUser = "http://localhost:8092/updateUser";
            RestTemplate updateUser = new RestTemplate();
            updateUser.put(urlUpdateUser, currentUser, User.class);
        }
    }

    public List<Media> getWatchlist() {

        String urlUser = "http://localhost:8092/currentUser";
        RestTemplate templateUser = new RestTemplate();

        String urlMedia = "http://localhost:8095/GetMedia?title={title}";
        RestTemplate templateMedia = new RestTemplate();

        User currentUser = templateUser.getForObject(urlUser, User.class);
        List<String> userFavorites = currentUser.getWatchlist();

        if(userFavorites == null || userFavorites.isEmpty()) {
            return new LinkedList<>();
        }

        List<Media> watchlist = new LinkedList<>();
        for(String media : userFavorites) {
            Media mediaResponse = templateMedia.getForObject(urlMedia, Media.class, media);
            watchlist.add(mediaResponse);
        }
        return watchlist;
    }

}