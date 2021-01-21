package com.watchlist.controller;


import com.watchlist.model.Media;
import com.watchlist.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.watchlist.configuration.WatchlistConstants.API_V1;

@RestController
@RequestMapping(API_V1)
@CrossOrigin(maxAge = 3600)
public class WatchlistController {

    private WatchlistService WatchlistService;

    @Autowired
    public WatchlistController(WatchlistService WatchlistService) {
        this.WatchlistService = WatchlistService;
    }

    @DeleteMapping(value = "/deleteWatchlist")
    public void removeFavourite(@RequestParam String mediaId) {
        WatchlistService.removeWatchlist(mediaId);
    }

    @PutMapping(value = "/addToWatchlist")
    public void addFavourite(@RequestParam String mediaId) {
        WatchlistService.addToWatchlist(mediaId);
    }

    @GetMapping(value = "/watchlist", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Media> getWatchlist() {
        return WatchlistService.getWatchlist();
    }


}
