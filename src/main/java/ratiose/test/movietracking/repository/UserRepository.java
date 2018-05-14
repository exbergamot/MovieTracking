package ratiose.test.movietracking.repository;

import org.springframework.data.repository.CrudRepository;
import ratiose.test.movietracking.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
