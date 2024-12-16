package com.example.kidsstore_tz.service.interfaces;

import com.example.kidsstore_tz.domain.User;

public interface IUserService {

    User getUserByEmail(String email);

    User saveUser(User user);

}
