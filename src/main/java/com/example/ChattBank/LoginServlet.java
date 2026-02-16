package com.example.ChattBank;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //3a: read parameters from Login Form
        String id = request.getParameter("customerId");
        String Password = request.getParameter("password");

        // display values in the Server Log
        System.out.println("LoginServlet called");
        System.out.println("Customer ID: " + id);
        System.out.println("Password: " + Password);

        // prepare HTML response
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");

        // 3b and 3c: validate login
        if ("admin".equals(id) && "123".equals(Password)) {
            out.println("<h1>Valid Login</h1>");
        } else {
            out.println("<h1>InValid Login</h1>");
        }

        out.println("</body></html>");
    }
}
