package com.example.ChattBank.AccountObject;

import java.sql.*;
import java.util.ArrayList;

public class AccountList {
    private ArrayList<Account> accounts;
    private int count;

    // Constructor
    public AccountList() {
        accounts = new ArrayList<>();
        count = 0;
    }

    // Getters
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public int getCount() {
        return count;
    }

    // add method
    public void addAccount(Account a1) {
        accounts.add(a1);   //put new Account object a1 into the AccountList
        count++;            //increase the count by 1
    }

    // display method
    public void displayAccounts() {
        System.out.println("============ Account List =================");
        for (Account a : accounts) {
            a.display();
        }
        System.out.println("Total Accounts: " + count);
        System.out.println("===========================================");
    }

    // Testing Lab5 done through Customer
}
