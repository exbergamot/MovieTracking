package ratiose.test.movietracking.controller;

import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.model.movie.MovieBasic;
import com.omertron.themoviedbapi.model.movie.MovieInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.*;
import ratiose.test.movietracking.entity.User;
import ratiose.test.movietracking.entity.WatchedMovie;
import ratiose.test.movietracking.service.MovieService;
import ratiose.test.movietracking.service.TmdbService;

import javax.servlet.http.HttpSession;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private TmdbService tmdbService;

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<MovieInfo> getMoviesByQuery(@RequestParam String query,
                                            @RequestParam(required = false) Integer page)
            throws MovieDbException {
        if (nonNull(page)) {
            return tmdbService.findMovieByQuery(query, page);
        } else {
            return tmdbService.findMovieByQuery(query);
        }
    }

    @RequestMapping(value = "/addFavourite", method = RequestMethod.POST)
    public WatchedMovie addWatchedMovieById(@RequestParam Long movieId,
                                            HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (isNull(user)) {
            throw new AuthorizationServiceException("please login first");
        }
        return movieService.markMovieWatched(user, movieId);
    }

    @RequestMapping(value = "/getRecommendations", method = RequestMethod.GET)
    public List<MovieBasic> getRecommendations(@RequestParam Integer year,
                                               @RequestParam Integer month,
                                               HttpSession session) throws MovieDbException {
        User user = (User) session.getAttribute("user");
        if (isNull(user)) {
            throw new AuthorizationServiceException("please login first");
        }
        return movieService.getRecommendations(user, month, year);
    }
}
