package com.hussir.micromall.service;

import com.hussir.micromall.view.Response;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    Response doLogin(String username, String password, HttpServletRequest request);
}
