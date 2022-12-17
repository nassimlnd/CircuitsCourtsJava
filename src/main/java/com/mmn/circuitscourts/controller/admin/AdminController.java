package com.mmn.circuitscourts.controller.admin;

import com.mmn.circuitscourts.views.ViewFactory;
import javafx.scene.input.MouseEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminController {

    public boolean isValidPassword(String password) {
        if (password == null || password.equals("")) {
            return false;
        }
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{8, 20}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        return m.matches();
    }

    public void onDashboardButton(){
        ViewFactory.getInstance().showAdminDashboardInterface();
    }
}
