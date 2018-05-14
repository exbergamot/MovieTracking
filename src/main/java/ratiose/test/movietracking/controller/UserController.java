package ratiose.test.movietracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ratiose.test.movietracking.entity.User;
import ratiose.test.movietracking.service.UserService;

import javax.servlet.http.HttpSession;

import static java.util.Objects.nonNull;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static ratiose.test.movietracking.controller.ControllerConstants.USER_ATTRIBUTE;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = POST)
    public User registerUser(@RequestParam String email,
                             @RequestParam String password,
                             HttpSession session) {
        return userService.registerUser(email, password);
    }

    @RequestMapping(value = "/login", method = POST)
    public User login(@RequestParam String email,
                      @RequestParam String password,
                      HttpSession session) {
        User foundUser = userService.findUser(email, password);
        if (nonNull(foundUser)) {
            session.setAttribute("user", foundUser);
            return foundUser;
        }
        throw new IllegalArgumentException("Wrong credentials");
    }

    @RequestMapping(value = "/logout", method = POST)
    public User logout(HttpSession session) {
        session.invalidate();
        return null;
    }
}
