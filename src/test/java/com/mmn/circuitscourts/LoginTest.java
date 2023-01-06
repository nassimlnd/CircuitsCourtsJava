package com.mmn.circuitscourts;

import com.mmn.circuitscourts.controller.LoginController;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class LoginTest extends TestCase {

    @Test
    void checkLogin() {
        assertEquals(App.userConnected.getIdentifiant(), "admin");
    }
}
