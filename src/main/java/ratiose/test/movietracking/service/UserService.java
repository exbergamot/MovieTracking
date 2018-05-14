package ratiose.test.movietracking.service;

import ratiose.test.movietracking.entity.User;

public interface UserService {
    User registerUser(String email, String password);
    User findUser(String email, String password);
}
