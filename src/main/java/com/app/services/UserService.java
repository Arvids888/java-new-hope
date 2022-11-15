package com.app.services;

import com.app.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("test1", "test1", "test1@test.lv", "01"));
        users.add(new User("test2", "test2", "test2@test.lv", "02"));
        users.add(new User("test3", "test3", "test3@test.lv", "03"));
        users.add(new User("test4", "test4", "test4@test.lv", "04"));
        users.add(new User("test5", "test5", "test5@test.lv", "05"));
        users.add(new User("test6", "test6", "test6@test.lv", "06"));
        users.add(new User("test7", "test7", "test7@test.lv", "07"));
        users.add(new User("test8", "test8", "test8@test.lv", "08"));
        users.add(new User("test9", "test9", "test9@test.lv", "09"));
        users.add(new User("test10", "test10", "test10@test.lv", "10"));

        return users;
    }

    public User validateUser(User user) {
        if (user.getFirstName().isEmpty() || user.getLastName().isEmpty() ||
        user.getEmail().isEmpty() || user.getPhone().isEmpty()) {
            return null;
        }
        String phone = user.getPhone().trim().replaceAll("\\(", "")
                .replaceAll("\\)", "");
        user.setPhone(phone);

        return user;
    }
}
