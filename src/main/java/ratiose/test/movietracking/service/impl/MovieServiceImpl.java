package ratiose.test.movietracking.service.impl;

import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.model.movie.MovieBasic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ratiose.test.movietracking.entity.FavouriteActor;
import ratiose.test.movietracking.entity.User;
import ratiose.test.movietracking.entity.WatchedMovie;
import ratiose.test.movietracking.repository.WatchedMovieRepository;
import ratiose.test.movietracking.service.FavouriteActorService;
import ratiose.test.movietracking.service.MovieService;
import ratiose.test.movietracking.service.TmdbService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service responsible for watched movie tracking and creating reccomendations
 */
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private WatchedMovieRepository watchedMovieRepository;

    @Autowired
    private FavouriteActorService favouriteActorService;

    @Autowired
    private TmdbService tmdbService;

    /**
     * Create record that mark movie as watched by given user
     * @param user - person who watched the movie
     * @param movieId - id of watched movie
     * @return - newly created record
     */
    @Override
    public WatchedMovie markMovieWatched(User user, Long movieId) {
        WatchedMovie watchedMovie = new WatchedMovie(user.getId(), movieId);
        return watchedMovieRepository.save(watchedMovie);
    }

    /**
     * Create list of recommended movies based on user's favourites actor
     * @param user - for who recommendations creates
     * @param month - month of release
     * @param year - year of release
     * @return - list of movies that recommended to watch for given user
     * @throws MovieDbException
     */
    @Override
    public List<MovieBasic> getRecommendations(User user, Integer month, Integer year) throws MovieDbException {
        List<FavouriteActor> favouriteActorsByUser = favouriteActorService.getFavouriteActorsByUser(user);
        List<Long> actorIds = favouriteActorsByUser.stream()
                .map(FavouriteActor::getActorId)
                .collect(Collectors.toList());
        List<MovieBasic> movieList = tmdbService.discoverMovies(actorIds, month, year);
        return excludeWatchedMovies(user, movieList);
    }


    private List<MovieBasic> excludeWatchedMovies(User user, List<MovieBasic> movieList) {
        List<Long> watchedMovieIds = watchedMovieRepository.getWatchedMoviesByUserId(user.getId()).stream()
                .map(WatchedMovie::getMovieId)
                .collect(Collectors.toList());
        return movieList.stream()
                .filter(each -> watchedMovieIds.contains(each.getId()))
                .collect(Collectors.toList());
    }
}
