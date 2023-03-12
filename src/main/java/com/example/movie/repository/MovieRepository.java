package com.example.movie.repository;
import java.util.*;
import com.example.movie.model.Movie;

public interface MovieRepository{
    ArrayList<Movie> getMovies();
    Movie getMovie(int movieId);
    Movie addMovie(Movie movieList);
    Movie updateMovie(int movieId, Movie movieList);
    void deleteMovie(int movieId);
}