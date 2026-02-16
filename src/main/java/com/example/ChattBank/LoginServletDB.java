package com.example.ChattBank;

import com.example.ChattBank.CustomerObject.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "LoginServletDB", value = "/LoginServletDB")
public class LoginServletDB extends HttpServlet{

    // For Lab 3 only before Business Objects (Customer and Account) were created w/ DB connection
    // path where ChattBank database file is located
    //private static final String DB_FILE = "C:/Users/Ravid/Desktop/School/Spring 2026/Java III/JavaEEHelloWorld/ChattBankACCDB.accdb";
    //private static final String JDBC_URL = "jdbc:ucanaccess:///" + DB_FILE;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("customerId");     // form ID
        String password = request.getParameter("password"); //form password

        // 4a Log to server console (read from form and do DB compare)
        System.out.println("LoginServletDB: CustID=" + id + ", password=" + password);

        boolean valid = false;

        // For Lab 3 only before Business Objects (Customer and Account) were created
        //Connection con = null;
        //PreparedStatement ps = null;
        //ResultSet rs = null;

        // added for Lab 4 part 1
        Customer c1 = new Customer();
        c1.selectDB(id); // load Customer object from DB
        String passwordDB = c1.getCustPassword(); // get password from object

        //try {
            //For Lab 3 only --> 1 load Driver
            //Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            //For Lab 3 only --> 2 get Connection
            //con = DriverManager.getConnection(JDBC_URL);

            //For Lab 3 only --> 3 create SQL statement
            //String sql = "select CustPassword from Customers where CustID=?";
            //ps = con.prepareStatement(sql);
            //ps.setString(1, id);

            //For Lab 3 only --> 4 execute
            //rs = ps.executeQuery();

            //For Lab 3 only --> 5 process data
            //if (rs.next()) {
                //String passwordDB = rs.getString("CustPassword");
                // 6 compare DB password to form password
                if (passwordDB != null && password.equals(passwordDB)) {
                    valid = true;
                }
            //}

            // return HTML result
            out.println("<html><head><title>Login Result</title></head><body>");
            if (valid) {
                out.println("<h1>Valid Login</h1>");
            } else {
                out.println("<h1>InValid Login</h1>");
            }
            out.println("</body></html>");

        }
        //catch (Exception e) {
            // If the DB path/driver/table names are wrong then will land here
            //e.printStackTrace();

            //out.println("<html><head><title>Error</title></head><body>");
            //out.println("<h1>Database Error</h1>");
            //out.println("<p>" + e.getMessage() + "</p>");
            //out.println("</body></html>");

        //} finally {
            // 6 Close in reverse order
            //try {
                //if (rs != null) rs.close();
            //} catch (Exception ignored) {
            //}
            //try {
                //if (ps != null) ps.close();
            //} catch (Exception ignored) {
           // }
            //try {
            //    if (con != null) con.close();
            //} catch (Exception ignored) {
            //}
            //out.close();
        //}
    //}

    // For Lab 3 only --> doGet() Method
    //protected void doGet(HttpServletRequest request, HttpServletResponse response)
    //        throws ServletException, IOException {
    //    processRequest(request, response);
    //}

    //doPost() Method
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}

