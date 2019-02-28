package com.hussir.micromall.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hussir.micromall.service.UserService;
import com.hussir.micromall.util.JsonUtils;
import com.hussir.micromall.view.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest request) throws JsonProcessingException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Response response = userService.doLogin(username, password, request);

        return JsonUtils.obj2String(response);
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login.jsp";
    }
}
