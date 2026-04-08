package com.example.auth.tests;

import com.example.auth.base.BaseTest;
import com.example.auth.pages.RegisterPage;
import com.example.auth.utils.TestDataFactory;
import org.junit.jupiter.api.*;

public class RegisterTest extends BaseTest {

  @Test
  void registerTest(){
    RegisterPage pageObj = new RegisterPage(page);
    pageObj.register(TestDataFactory.username(), TestDataFactory.password(), TestDataFactory.phone());
  }
}
