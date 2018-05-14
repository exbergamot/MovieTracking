package ratiose.test.movietracking.controller;

import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.model.person.PersonFind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.*;
import ratiose.test.movietracking.entity.FavouriteActor;
import ratiose.test.movietracking.entity.User;
import ratiose.test.movietracking.service.FavouriteActorService;
import ratiose.test.movietracking.service.TmdbService;
import ratiose.test.movietracking.service.impl.TmdbServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/actor")
public class ActorsController {
    @Autowired
    private TmdbService tmdbService;

    @Autowired
    private FavouriteActorService favouriteActorService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<PersonFind> getActorsByQuery(@RequestParam String query,
                                             @RequestParam(required = false) Integer page)
                                                throws MovieDbException {
        if (nonNull(page)) {
            return tmdbService.findPersonByQuery(query, page);
        } else {
            return tmdbService.findPersonByQuery(query);
        }
    }

    @RequestMapping(value = "/addFavourite", method = RequestMethod.POST)
    public FavouriteActor addActorById(@RequestParam Long actorId,
                                       HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (isNull(user)) {
            throw new AuthorizationServiceException("please login first");
        }
        return favouriteActorService.createFavouriteActor(user, actorId);
    }

    @RequestMapping(value = "/removeFavourite", method = RequestMethod.POST)
    public Long removeActorById(@RequestParam Long actorId,
                                       HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (isNull(user)) {
            throw new AuthorizationServiceException("please login first");
        }
        favouriteActorService.removeFavouriteActor(user, actorId);
        return actorId;
    }

}
