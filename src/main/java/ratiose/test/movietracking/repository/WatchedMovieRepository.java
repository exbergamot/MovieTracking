package ratiose.test.movietracking.repository;

import org.springframework.data.repository.CrudRepository;
import ratiose.test.movietracking.entity.WatchedMovie;

import java.util.List;

public interface WatchedMovieRepository extends CrudRepository<WatchedMovie, WatchedMovie.WatchedMovieId> {
    List<WatchedMovie> getWatchedMoviesByUserId(Long userId);
}
