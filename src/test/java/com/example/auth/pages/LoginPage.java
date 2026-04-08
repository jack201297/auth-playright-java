package com.example.auth.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LoginPage extends BasePage {

    private final String CLOSE_POPUP = "//div[@class='absolute -top-[10%] right-[3%] z-[2] cursor-pointer hover:opacity-90 xl:-top-[8%] xl:right-0']";
    private final String SIGN_IN = "internal:role=button[name=\"Đăng nhập\"i]";
    private final String USERNAME = "//form//input[@name='username']";
    private final String PASSWORD = "//form//input[@name='password']";
    private final String SUBMIT = "//form//button[@type='submit' and normalize-space()='Đăng nhập']";
    private final String CLOSE_POPUP_2 = "div[class='absolute right-2.5 top-4 flex h-6 w-7 items-center justify-center rounded p-0'] svg";
    private final String VERIFY_SUCCESS = "//button[normalize-space()='Nạp tiền']";

    public LoginPage(Page page) {
        super(page);
    }

    public void closePopupIfPresent() {
        Locator popup = page.locator(CLOSE_POPUP).first();
        try {
            popup.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(3000));
            popup.click(new Locator.ClickOptions().setForce(true));
            page.waitForTimeout(500); // Wait for page to stabilize after closing popup
        } catch (PlaywrightException e) {
            System.out.println("Popup not displayed before login, skip closing.");
        }
    }

    public void openLogin() {
        page.locator(SIGN_IN).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
        page.locator(SIGN_IN).click(new Locator.ClickOptions().setForce(true));
    }

    public void fillLoginForm(String user, String pass) {
        fill(USERNAME, user);
        fill(PASSWORD, pass);
    }

    public void submit() {
        click(SUBMIT);
    }

    public void closePopupAfterLoginIfPresent() {
        Locator popup2 = page.locator(CLOSE_POPUP_2).first();
        try {
            popup2.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(3000));
            popup2.click(new Locator.ClickOptions().setForce(true));
        } catch (PlaywrightException e) {
            System.out.println("Post-login popup not displayed, skip closing.");
        }
    }

    public void login(String user, String pass) {
        closePopupIfPresent();
        openLogin();
        fillLoginForm(user, pass);
        submit();
//        closePopupAfterLoginIfPresent();
        verifySuccessPageElements();
    }

    public boolean isSuccess() {
        return page.locator(VERIFY_SUCCESS).isVisible();
    }

    public void verifyLoginSuccess() {
        // Wait for success element to appear with timeout
        page.locator(VERIFY_SUCCESS).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(10000));
        System.out.println("✓ Login success verified - 'Deposit' button appeared");
    }

    public void verifySuccessPageElements() {
        // Verify multiple elements appear after login
        verifyLoginSuccess();

        if (isSuccess()) {
            System.out.println("✓ User successfully logged in and redirected to dashboard");
        } else {
            throw new AssertionError("Login verification failed - Success element not visible");
        }
    }
}