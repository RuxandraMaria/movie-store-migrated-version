package com.favorites.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Media {

    public String title;
    public LocalDate release;
    public URI imageUri;
    public String description;
    public String director;
    public Set<String> genres;
    public String type;
    public Set<String> actors;
    public Double averageRating;

    public Media() {}

}