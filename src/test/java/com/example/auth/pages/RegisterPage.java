package com.example.auth.pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.microsoft.playwright.PlaywrightException;

public class RegisterPage extends BasePage {

    private final String CLOSE_POPUP = "//div[@class='absolute -top-[10%] right-[3%] z-[2] cursor-pointer hover:opacity-90 xl:-top-[8%] xl:right-0']";
    private final String SIGN_UP = "internal:role=button[name=\"Đăng ký\"i]";
    private final String USERNAME = "//input[@name='username']";
    private final String PASSWORD = "//input[@name='password']";
    private final String PHONE = "//input[@name='phone_number']";
    private final String SUBMIT = "(//button[@type='submit'])[1]";
    private final String VERIFY_SUCCESS = "(//p[contains(@class,'text-xl')])[1]";

    public RegisterPage(Page page){
        super(page);
    }

    public void closePopupIfPresent() {
        Locator popup = page.locator(CLOSE_POPUP).first();
        try {
            popup.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(3000));
            popup.click(new Locator.ClickOptions().setForce(true));
            page.waitForTimeout(500); // Wait for page to stabilize after closing popup
        } catch (PlaywrightException e) {
            System.out.println("Popup not displayed before register, skip closing.");
        }
    }

    public void openRegister(){
        page.locator(SIGN_UP).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
        page.locator(SIGN_UP).click(new Locator.ClickOptions().setForce(true));
    }

    public void register(String u, String p, String phone){
        closePopupIfPresent();
        openRegister();
        fill(USERNAME, u);
        fill(PASSWORD, p);
        fill(PHONE, phone);
        click(SUBMIT);
        verifyRegisterSuccess();
    }

    public boolean isSuccess(){
        return page.locator(VERIFY_SUCCESS).isVisible();
    }

    public void verifyRegisterSuccess() {
        // Wait for success element to appear with timeout
        try {
            page.locator(VERIFY_SUCCESS).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(10000));
            if (isSuccess()) {
                System.out.println("✓ Register success verified - Success message appeared");
            } else {
                System.out.println("✗ Register failed - Success message not found");
                throw new AssertionError("Register verification failed - Success element not visible");
            }
        } catch (PlaywrightException e) {
            System.out.println("✗ Register failed - Timeout waiting for success message");
            throw new AssertionError("Register verification failed - Element did not appear within timeout");
        }
    }

    public void verifySuccessPageElements() {
        // Verify multiple elements appear after registration
        verifyRegisterSuccess();

        if (isSuccess()) {
            System.out.println("✓ User successfully registered");
        } else {
            throw new AssertionError("Register verification failed - Success element not visible");
        }
    }
}
