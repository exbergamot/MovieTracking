package ratiose.test.movietracking.service;

import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.model.movie.MovieBasic;
import com.omertron.themoviedbapi.model.movie.MovieInfo;
import com.omertron.themoviedbapi.model.person.PersonFind;

import java.util.List;

/**
 * Service for access movie and person data from tmdb
 */
public interface TmdbService {

    public List<PersonFind> findPersonByQuery(String query) throws MovieDbException;

    public List<PersonFind> findPersonByQuery(String query, int page) throws MovieDbException;

    public List<MovieInfo> findMovieByQuery(String query, int page) throws MovieDbException;

    public List<MovieInfo> findMovieByQuery(String query) throws MovieDbException;

    public List<MovieBasic> discoverMovies(List<Long> actors, Integer month, Integer year) throws MovieDbException;
}
