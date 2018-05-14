package ratiose.test.movietracking.service;

import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.model.movie.MovieBasic;
import ratiose.test.movietracking.entity.User;
import ratiose.test.movietracking.entity.WatchedMovie;

import java.util.List;

public interface MovieService {
    WatchedMovie markMovieWatched(User user, Long movieId);
    List<MovieBasic> getRecommendations(User user, Integer month, Integer year) throws MovieDbException;
}
