package com.example.auth.tests;

import com.example.auth.base.BaseTest;
import com.example.auth.pages.LoginPage;
import com.example.auth.pages.HomePage;
import org.junit.jupiter.api.*;

public class LogoutTest extends BaseTest {

    @Test
    void logoutTest(){
        LoginPage login = new LoginPage(page);
        HomePage home = new HomePage(page);

        // Step 1: Login first
        login.login("atcaha1","A1234567");

        // Step 2: Logout and verify
        home.logout();
        home.verifyLogoutSuccess();
    }
}
