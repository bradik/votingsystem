package com.example.votingsystem.service.impl;

import com.example.votingsystem.web.AuthorizedUser;
import com.example.votingsystem.model.User;
import com.example.votingsystem.repository.UserRepository;
import com.example.votingsystem.service.UserService;
import com.example.votingsystem.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brad on 03.10.2017.
 */

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository dao;

    @Override
    public User getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public User getByEmail(String email) {
        return dao.findByEmail(email.toLowerCase());
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        result = dao.findAll();
        return result;
    }

    @Override
    public User save(User user) {

        return dao.save(UserUtil.prepareToSave(user));
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
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
