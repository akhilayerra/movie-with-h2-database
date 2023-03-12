package com.example.movie.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.movie.model.Movie;
import com.example.movie.model.MovieRowMapper;
import com.example.movie.repository.MovieRepository;

@Service
public class MovieH2Service implements MovieRepository{
 @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Movie> getMovies(){
        List<Movie> movies1=db.query("select * from MovieList",new MovieRowMapper());
        ArrayList<Movie> movies=new ArrayList<>(movies1);
        return movies;
    }

    @Override
    public Movie getMovie(int movieId){
        try{
        Movie movie=db.queryForObject("select * from movieList where movieId=?", new MovieRowMapper(),movieId);
        return movie;
        }catch (Exception e){

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }
    @Override
    public Movie addMovie(Movie movieList){
        db.update("insert into movieList(movieName,leadActor) values(?,?)",movieList.getMovieName(),movieList.getLeadActor());
        Movie savedMovie=db.queryForObject("select * from movieList where movieName=? and leadActor=?", new MovieRowMapper(),movieList.getMovieName(),movieList.getLeadActor());
    return savedMovie;
    }
    @Override
    public Movie updateMovie(int movieId,Movie movieList){
        try{
        if(movieList.getMovieName()!=null){
            db.update("update movieList set movieName=? where movieId=?",movieList.getMovieName(),movieId);
        }
        if(movieList.getLeadActor()!=null){
            db.update("update movieList set leadActor=? where movieId=?",movieList.getLeadActor(),movieId);
        }
        return getMovie(movieId);
        }catch (Exception e){

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      } 
    }
    @Override
    public void deleteMovie(int movieId){
        try{
        db.update("delete from movieList where id = ?", movieId);  
        }
        catch (Exception e){

        throw new ResponseStatusException(HttpStatus.OK);
      }  
    }
}
