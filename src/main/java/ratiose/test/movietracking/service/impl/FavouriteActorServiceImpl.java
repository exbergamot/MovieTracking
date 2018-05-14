package ratiose.test.movietracking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ratiose.test.movietracking.entity.FavouriteActor;
import ratiose.test.movietracking.entity.User;
import ratiose.test.movietracking.repository.FavouriteActorRepository;
import ratiose.test.movietracking.service.FavouriteActorService;

import java.util.List;

/**
 * Service responsible for favourite actors tracking
 */
@Service
public class FavouriteActorServiceImpl implements FavouriteActorService {

    @Autowired
    private FavouriteActorRepository repository;

    /**
     * Create record about favourite actor
     * @param user - user who likes the actor
     * @param actorId - id of favourite actor
     * @return newly created record about favourite actor
     */
    @Override
    public FavouriteActor createFavouriteActor(User user, Long actorId) {
        FavouriteActor favouriteActor = new FavouriteActor(user.getId(), actorId);
        return repository.save(favouriteActor);
    }

    /**
     * Remove record about favourite actor
     * @param user - user who no more likes the actor
     * @param actorId - id of  actor
     */
    @Override
    public void removeFavouriteActor(User user, Long actorId) {
        FavouriteActor favouriteActor = new FavouriteActor(user.getId(), actorId);
        repository.delete(favouriteActor);
    }

    /**
     * Return list of favourite actors for given user
     * @param user - user whom list will be returned
     * @return favourite actors list
     */
    @Override
    public List<FavouriteActor> getFavouriteActorsByUser(User user) {
        return repository.getFavouriteActorsByUserId(user.getId());
    }
}
