package com.danny.rest.webservices.dto;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int usersCount;

    static {
        users.add(new User(++usersCount, "Danny", new Date()));
        users.add(new User(++usersCount, "Beeni", new Date()));
        users.add(new User(++usersCount, "Totha", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id) {
        User user = findById(id);
        users.remove(user);
        return user;
    }

}
