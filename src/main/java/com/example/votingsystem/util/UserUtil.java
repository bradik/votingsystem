package com.example.votingsystem.util;


import com.example.votingsystem.model.Roles;
import com.example.votingsystem.model.User;
import com.example.votingsystem.web.to.UserTo;

public class UserUtil {
    public static User createNewFromTo(UserTo newUser) {
        return new User(null, newUser.getEmail(), newUser.getPassword(), Roles.USER);
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getEmail(), user.getPassword());
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setEmail(userTo.getEmail());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static User prepareToSave(User user) {
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
