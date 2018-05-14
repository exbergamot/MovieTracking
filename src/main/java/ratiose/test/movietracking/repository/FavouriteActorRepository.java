package ratiose.test.movietracking.repository;

import org.springframework.data.repository.CrudRepository;
import ratiose.test.movietracking.entity.FavouriteActor;

import java.util.List;

public interface FavouriteActorRepository extends CrudRepository<FavouriteActor, FavouriteActor.FavouriteActorId> {
    List<FavouriteActor> getFavouriteActorsByUserId(Long userId);
}
