package com.example.ChattBank;


import com.example.ChattBank.AccountObject.Account;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AccountLookupServlet", value = "/AccountLookupServlet")
public class AccountLookupServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Lab 7 Read AccountNo from the login form
        String acctNo = request.getParameter("acctNo");
        if (acctNo == null) acctNo = "";
        acctNo = acctNo.trim();

        // removed for Lab 7
        //String custId  = request.getParameter("custId");
        //String type    = request.getParameter("type");
        //String balance = request.getParameter("balance");

        System.out.println("AccountLookupServlet called. acctNo = " + acctNo);

        // Lab 7 Use AccountNo to create/load the business object from DB
        Account a1 = new Account();
        a1.selectDB(acctNo);

        // Display to Server Log (Lab 6 requirement)
        System.out.println("=== Account from DB ===");
        System.out.println("AcctNo   : " + a1.getAcctNo());
        System.out.println("CustID   : " + a1.getCustId());
        System.out.println("Type     : " + a1.getType());
        System.out.println("Balance  : " + a1.getBalance());
        System.out.println("=======================");

        // Step 6: put Account object in the Session
        HttpSession ses1 = request.getSession();
        ses1.setAttribute("a1", a1);  // name 'a1' is common in your labs

        // Lab 7 Forward control to DisplayAccount.jsp
        RequestDispatcher rd = request.getRequestDispatcher("/DisplayAccount.jsp");
        rd.forward(request, response);

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
