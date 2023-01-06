package com.itcast.web.servlet;/*
Author: Inklo
Time: 2022/4/7 8:44
Target: 
*/

import com.itcast.service.UserService;
import com.itcast.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/allUsers")
public class AllUsersServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter outer = response.getWriter();
        String users = userService.findAllUsersString();
        outer.print(users);
        outer.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
