package com.example.ChattBank.AccountObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Account {
    // ======================  Properties  ================
    private String acctNo;
    private String custId;
    private String type;
    private long balance;

    // ======================== Constructors ==============
    public Account() {
        acctNo = "";
        custId = "";
        type = "";
        balance = 0;
    }

    public Account(String AcctNo, String Cid, String Type, long Balance) {
        acctNo = AcctNo;
        custId = Cid;
        type = Type;
        balance = Balance;
    }

    // ==================================  Behaviors ====================
    public String getAcctNo() { return acctNo; }
    public String getCustId() { return custId; }
    public String getType() { return type; }
    public long getBalance() { return balance; }

    public void setAcctNo(String AcctNo) { this.acctNo = AcctNo; }
    public void setCustId(String Cid) { this.custId = Cid; }
    public void setType(String Type) { this.type = Type; }
    public void setBalance(long Balance) { this.balance = Balance; }

    public void display() {
        System.out.println("=======================================");
        System.out.println("Account Number  : " + getAcctNo());
        System.out.println("Customer ID     : " + getCustId());
        System.out.println("Type            : " + getType());
        System.out.println("Balance         : " + getBalance());
        System.out.println("=======================================");
    }

    // ++++++++++ DB Behaviors +++++++++++++
    // selectDB method
    public void selectDB(String AcctNo){
        acctNo = AcctNo;
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Ravid/Desktop/School/Spring 2026/Java III/JavaEEHelloWorld/ChattBankACCDB.accdb");
            Statement stmt = con.createStatement();

            String sql;
            sql = "SELECT * FROM Accounts WHERE AcctNo = '" + getAcctNo()+ "'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();

            setCustId(rs.getString("Cid"));
            setType(rs.getString("Type"));
            setBalance(rs.getLong("Balance"));

        }
        catch(Exception e){
            System.out.println(e);
        }
    } //end selectDB()

    // insertDB method
    public void insertDB(String AcctNo, String Cid, String Type, long Balance) {
        acctNo = AcctNo;
        custId = Cid;
        type = Type;
        balance = Balance;

        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Ravid/Desktop/School/Spring 2026/Java III/JavaEEHelloWorld/ChattBankACCDB.accdb");
            Statement stmt = con.createStatement();

            String sql;
            sql = "INSERT INTO Accounts VALUES ('" + getAcctNo()+ "',"+
                    "'"+getCustId()+"',"+
                    "'"+getType()+"',"+
                    "'"+getBalance()+"')";

            System.out.println();
            System.out.println(sql);
            System.out.println();
            int n1 = stmt.executeUpdate(sql);
            if(n1==1)
                System.out.println("Account inserted successfully");
            else
                System.out.println("Account insert failed");
            con.close();
        }
        catch(Exception e2){
            System.out.println(e2);
        }
    } //end insertDB()

    // deleteDB method
    public void deleteDB(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Ravid/Desktop/School/Spring 2026/Java III/JavaEEHelloWorld/ChattBankACCDB.accdb");
            Statement stmt = con.createStatement();

            String sql;
            sql = "DELETE from Accounts where AcctNo = '" + getAcctNo()+ "'";
            System.out.println(sql);
            int n1 = stmt.executeUpdate(sql);
            if(n1==1)
                System.out.println("Account DELETE successfully");
            else
                System.out.println("Account DELETE failed");
            con.close();
        }
        catch(Exception e4){
            System.out.println(e4);
        }

    }// end deleteDB()

    public static void main(String[] args) {
        //Testing selectDB()
        System.out.println("=======================================");
        System.out.println("Testing selectDB");
        Account a1 = new Account();
        a1.selectDB("97474");
        a1.display();

        //Testing insertDB()
        System.out.println("=======================================");
        System.out.println("Testing insertDB");
        Account a2 = new Account();
        a2.insertDB("97575", "3075", "MMA", 1000);
        a2.display();
        //check database to see if new Student has been added

        //Testing deleteDB()
        System.out.println("=======================================");
        System.out.println("Testing deleteDB");
        Account a3 = new Account();
        a3.selectDB("97575");
        a3.deleteDB();
        System.out.println();
    }
}


