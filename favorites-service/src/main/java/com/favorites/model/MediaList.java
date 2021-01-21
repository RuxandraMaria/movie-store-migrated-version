package com.favorites.model;

import java.util.ArrayList;
import java.util.List;

public class MediaList {

    private List<Media> media;

    public MediaList() {
        media = new ArrayList<>();
    }

    public List<Media> getMediaList() {
        return media;
    }
}
