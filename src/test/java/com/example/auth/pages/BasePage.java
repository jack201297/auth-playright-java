package com.example.auth.pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

public class BasePage {
 protected Page page;

 public BasePage(Page page){
   this.page = page;
 }

 protected void click(String locator){
   page.locator(locator).waitFor();
   page.locator(locator).click(new Locator.ClickOptions().setForce(true));
 }

 protected void fill(String locator, String value){
   page.locator(locator).waitFor();
   page.locator(locator).fill(value);
 }
}
