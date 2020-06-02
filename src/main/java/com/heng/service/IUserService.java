package com.heng.service;

import com.heng.entity.User;
import org.springframework.stereotype.Service;


@Service
public interface IUserService {

    User getUserById(Integer uid);

    User getUserByUsername(String username);

    String getUserByEmail(String email);

    void updatePasswordByEmail(String email,  String password);

    int saveUser(String username, String password, String email);
}
