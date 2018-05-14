package ratiose.test.movietracking.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@IdClass(WatchedMovie.WatchedMovieId.class)
public class WatchedMovie {

    @Id
    private Long userId;

    @Id
    private Long movieId;

    public WatchedMovie(){};
    public WatchedMovie(Long userId, Long actorId) {
        this.userId = userId;
        this.movieId = actorId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public static class WatchedMovieId implements Serializable {
        private Long userId;
        private Long movieId;
    }
}
