package com.example.ChattBank.CustomerObject;

import com.example.ChattBank.AccountObject.Account;
import com.example.ChattBank.AccountObject.AccountList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer {
    // ======================  Properties  ================
    private String custId;
    private String custPassword;
    private String custFirstName;
    private String custLastName;
    private String custAddress;
    private String custEmail;
    private AccountList aList; // added for Lab 5

    // ======================== Constructors ==============
    public Customer() {
        custId = "";
        custPassword = "";
        custFirstName = "";
        custLastName = "";
        custAddress = "";
        custEmail = "";
        aList = new AccountList(); // added for Lab 5
    }

    public Customer(String CustID, String CustPassword, String CustFirstName, String CustLastName, String CustAddress, String CustEmail) {
        custId = CustID;
        custPassword = CustPassword;
        custFirstName = CustFirstName;
        custLastName = CustLastName;
        custAddress = CustAddress;
        custEmail = CustEmail;
        aList = new AccountList(); // added for Lab 5
    }

    // ==================================  Behaviors ====================
    // Getters
    public String getCustId() { return custId; }
    public String getCustPassword() { return custPassword; }
    public String getCustFirstName() { return custFirstName; }
    public String getCustLastName() { return custLastName; }
    public String getCustAddress() { return custAddress; }
    public String getCustEmail() { return custEmail; }

    // added for Lab 5
    public AccountList getAccountList() { return aList; }

    // Setters
    public void setCustId(String CustID) { this.custId = CustID; }
    public void setCustPassword(String CustPassword) { this.custPassword = CustPassword; }
    public void setCustFirstName(String CustFirstName) { this.custFirstName = CustFirstName; }
    public void setCustLastName(String CustLastName) { this.custLastName = CustLastName; }
    public void setCustAddress(String CustAddress) { this.custAddress = CustAddress; }
    public void setCustEmail(String CustEmail) { this.custEmail = CustEmail; }

    public void display() {
        System.out.println("=======================================");
        System.out.println("Customer Information");
        System.out.println("=======================================");
        System.out.println("Customer ID         : " + getCustId());
        System.out.println("Customer Password   : " + getCustPassword());
        System.out.println("Customer First Name : " + getCustFirstName());
        System.out.println("Customer Last Name  : " + getCustLastName());
        System.out.println("Customer Address    : " + getCustAddress());
        System.out.println("Customer Email      : " + getCustEmail());
        System.out.println("=======================================");

        // Lab 5 - add so the account list will be displayed
        if (aList != null) {
            aList.displayAccounts();
        } else {
            System.out.println("No Account List Found");
        }
            System.out.println("=======================================");
    }

    // ++++++++++ DB Behaviors +++++++++++++
    // selectDB method (loads customer + THEN loads accounts)
    public void selectDB(String CustID){
        custId = CustID;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Ravid/Desktop/School/Spring 2026/Java III/JavaEEHelloWorld/ChattBankACCDB.accdb");
            Statement stmt = con.createStatement();

            String sql;
            sql = "SELECT * FROM Customers WHERE CustID = '" + getCustId() + "'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()) {

            setCustPassword(rs.getString("CustPassword"));
            setCustFirstName(rs.getString("CustFirstName"));
            setCustLastName(rs.getString("CustLastName"));
            setCustAddress(rs.getString("CustAddress"));
            setCustEmail(rs.getString("CustEmail"));

            // added for Lab 5
            // call getAccounts() AFTER customer is loaded
            getAccountsFromDB();

        }else{
            System.out.println("Customer not found" + getCustId());
        }

        rs.close();
        stmt.close();
        con.close();

        }
        catch(Exception e){
            System.out.println(e);
        }
    } //end selectDB()

    // insertDB method
    public void insertDB(String CustID, String CustPassword, String CustFirstName, String CustLastName, String CustAddress, String CustEmail) {
        custId = CustID;
        custPassword = CustPassword;
        custFirstName = CustFirstName;
        custLastName = CustLastName;
        custAddress = CustAddress;
        custEmail = CustEmail;

        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Ravid/Desktop/School/Spring 2026/Java III/JavaEEHelloWorld/ChattBankACCDB.accdb");
            Statement stmt = con.createStatement();

            String sql;
            sql = "INSERT INTO Customers VALUES ('" + getCustId()+ "',"+
                    "'"+getCustPassword()+"',"+
                    "'"+getCustFirstName()+"',"+
                    "'"+getCustLastName()+"',"+
                    "'"+getCustAddress()+"',"+
                    "'"+getCustEmail()+"')";

            System.out.println();
            System.out.println(sql);
            System.out.println();
            int n1 = stmt.executeUpdate(sql);
            if(n1==1)
                System.out.println("Customer inserted successfully");
            else
                System.out.println("Customer insert failed");
            con.close();
        }
        catch(Exception e2){
            System.out.println(e2);
        }
    } //end insertDB()

    // Lab 5 method: getAccountsFromDB()
    //looks up all account numbers for this customer and adds each Account to aList
    public void getAccountsFromDB(){
        // reset list each time to avoid duplicates
        aList = new AccountList();

        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Ravid/Desktop/School/Spring 2026/Java III/JavaEEHelloWorld/ChattBankACCDB.accdb");
            Statement stmt = con.createStatement();

            String sql = "SELECT AcctNo FROM Accounts WHERE Cid = '" + getCustId() + "'";
            System.out.println(sql);

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String acctNo = rs.getString("AcctNo");

                Account a1 = new Account();     // empty account object
                a1.selectDB(acctNo);            // load full account info from DB (Account class method)
                aList.addAccount(a1);           // add to list
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // deleteDB method
    public void deleteDB(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Ravid/Desktop/School/Spring 2026/Java III/JavaEEHelloWorld/ChattBankACCDB.accdb");
            Statement stmt = con.createStatement();

            String sql;
            sql = "DELETE from Customers where CustID = '" + getCustId()+ "'";
            System.out.println(sql);
            int n1 = stmt.executeUpdate(sql);
            if(n1==1)
                System.out.println("Customer DELETE successfully");
            else
                System.out.println("Customer DELETE failed");
            con.close();
        }
        catch(Exception e4){
            System.out.println(e4);
        }

    }// end deleteDB()

    // Testing
    public static void main(String[] args) {
        //Testing Lab 4 selectDB()
        System.out.println("=======================================");
        System.out.println("Testing selectDB");
        Customer c1 = new Customer();
        c1.selectDB("3001");
        c1.display();

        //Testing Lab 4 insertDB()
        System.out.println("=======================================");
        System.out.println("Testing insertDB");
        Customer c2 = new Customer();
        c2.insertDB("3009", "9090", "Maria", "Ravid", "Atlanta", "mr@hotmail.com");
        c2.display();
        //check database to see if new Customer has been added

        //Testing Lab 4 deleteDB()
        System.out.println("=======================================");
        System.out.println("Testing deleteDB");
        Customer c3 = new Customer();
        c3.selectDB("3009");
        c3.deleteDB();
        System.out.println();

        //Testing Lab 5
        System.out.println("=======================================");
        System.out.println("Testing Customer.selectDB() with Accounts");
        Customer c4 = new Customer();
        c4.selectDB("3001");
        c4.display();
    }
}

