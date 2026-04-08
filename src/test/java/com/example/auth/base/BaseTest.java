package com.example.auth.base;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

public class BaseTest {
 protected Playwright playwright;
 protected Browser browser;
 protected BrowserContext context;
 protected Page page;

 @BeforeEach
 void setup() {
   playwright = Playwright.create();
   browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
   context = browser.newContext();
   page = context.newPage();
   page.navigate("https://haybet.cc");
 }

 @AfterEach
 void tearDown() {
   context.close();
   browser.close();
   playwright.close();
 }
}
