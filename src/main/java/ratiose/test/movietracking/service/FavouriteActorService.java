package ratiose.test.movietracking.service;

import ratiose.test.movietracking.entity.FavouriteActor;
import ratiose.test.movietracking.entity.User;

import java.util.List;

public interface FavouriteActorService {
    FavouriteActor createFavouriteActor(User user, Long actorId);
    void removeFavouriteActor(User user, Long actorId);
    List<FavouriteActor> getFavouriteActorsByUser(User user);
}
