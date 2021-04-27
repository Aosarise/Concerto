package com.example.concerto.service;

import com.example.concerto.pojo.LoginForm;
import com.example.concerto.pojo.RegisterForm;
import com.example.concerto.pojo.User;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 16:05 2021/4/25
 * @Version: 1.0$
 */

public interface UserService {

    public void Register(RegisterForm user);

    String login(LoginForm loginForm);

    public User getUserById(int id);
}