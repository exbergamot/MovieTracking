package ratiose.test.movietracking.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@IdClass(FavouriteActor.FavouriteActorId.class)
public class FavouriteActor {

    @Id
    private Long userId;
    @Id
    private Long actorId;

    public FavouriteActor(){};
    public FavouriteActor(Long userId, Long actorId) {
        this.userId = userId;
        this.actorId = actorId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getActorId() {
        return actorId;
    }

    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }

    public static class FavouriteActorId implements Serializable {
        private Long userId;
        private Long actorId;
    }
}
