package com.example.votingsystem.service.impl;

import com.example.votingsystem.model.User;
import com.example.votingsystem.repository.UserRepository;
import com.example.votingsystem.service.UserService;
import com.example.votingsystem.util.UserUtil;
import com.example.votingsystem.web.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.example.votingsystem.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Brad on 03.10.2017.
 */

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Sort SORT_ROLE_EMAIL = new Sort("role", "email");

    @Autowired
    private UserRepository dao;

    @Override
    public User getById(Integer id) {
        return checkNotFoundWithId(dao.getById(id), id);
    }

    @Override
    public User getByEmail(String email) {
        return dao.findByEmail(email.toLowerCase());
    }

    @Override
    public List<User> getAll() {
        return dao.findAll(SORT_ROLE_EMAIL);
    }

    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return dao.save(UserUtil.prepareToSave(user));
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(dao.delete(id) != 0, id);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = dao.findByEmail(email.toLowerCase());
        if (u == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(u);
    }
}
