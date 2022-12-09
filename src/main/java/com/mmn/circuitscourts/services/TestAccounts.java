package com.mmn.circuitscourts.services;

import com.mmn.circuitscourts.models.User;

import java.sql.SQLException;

public class TestAccounts {

    public static void main(String[] args) {
        User user = new User("nassim", "1234", 2);
        AccountDAO accountDAO = new AccountDAO();

        try {
            accountDAO.add(user);
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }


    }
}
