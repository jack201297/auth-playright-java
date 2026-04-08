package com.example.auth.tests;

import com.example.auth.base.BaseTest;
import com.example.auth.pages.LoginPage;
import org.junit.jupiter.api.*;

public class LoginTest extends BaseTest {

  @Test
  void loginTest(){
    LoginPage pageObj = new LoginPage(page);
    pageObj.login("atcaha1","A1234567");
  }
}
