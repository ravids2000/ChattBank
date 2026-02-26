package com.example.ChattBank;

import com.example.ChattBank.CustomerObject.Customer;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
// login updated in Lab 6.
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Step #1 - Read data from login.jsp
        String id = request.getParameter("customerId");     // form ID
        String password = request.getParameter("password"); //form password

        System.out.println("LoginServlet: customerId=" + id + ", password=" + password);

        // Basic null or empty guard
        if (id == null) id = "";
        if (password == null) password = "";

        // Step #3 - Create objects needed (Customer BO) and do DB lookup
        // added for Lab 4 part 1
        Customer c1 = new Customer();
        c1.setCustId(id);
        c1.selectDB(id); // load Customer object from DB
        String passwordDB = c1.getCustPassword(); // get password from object

        // Step #4 - Make decision: valid login?
        boolean valid = (passwordDB != null && !passwordDB.isEmpty() && password.equals(passwordDB));

        // Step #5 - Put objects in session if needed by next page/servlet
        HttpSession ses1 = request.getSession();
        ses1.setAttribute("c1", c1);  // store Customer ALWAYS store it (valid or invalid) updated for Lab7

        // Step #6 - Forward to AccountLookup.jsp
        if (valid) {
            System.out.println("LoginServlet: Valid login. Customer stored in session as 'c1'.");
            RequestDispatcher rd = request.getRequestDispatcher("/accountLookup.jsp");
            rd.forward(request, response);
        } else {
            // error message for ErrorPage.jsp to show
            request.setAttribute("errorMsg", "Invalid Customer ID or Password.");

            System.out.println("LoginServlet: Invalid login. Forwarding to ErrorPage.jsp");
            RequestDispatcher rd = request.getRequestDispatcher("/ErrorPage.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
