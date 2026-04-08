package com.example.auth.pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.microsoft.playwright.PlaywrightException;

public class HomePage extends BasePage {

    private final String CLOSE_POPUP = "//div[@class='absolute -top-[10%] right-[3%] z-[2] cursor-pointer hover:opacity-90 xl:-top-[8%] xl:right-0']";
    private final String DEPOSIT_BUTTON = "internal:role=button[name=\"Nạp tiền\"i]";
    private final String LOGOUT_BUTTON = "//div[normalize-space()='Đăng xuất']";
    private final String CONFIRM_LOGOUT = "button[class='focus-visible:ring-ring inline-flex select-none items-center justify-center gap-2 whitespace-nowrap rounded-lg font-semibold transition-colors focus-visible:outline-none focus-visible:ring-1 disabled:pointer-events-none [&_svg]:pointer-events-none [&_svg]:shrink-0 border-primary-400 text-primary-400 border disabled:border-neutral-600 disabled:bg-neutral-800 disabled:text-neutral-500 h-10 px-8 lg:hover:text-primary-400 lg:hover:border-primary-400 w-full bg-[#001A0A] text-sm uppercase lg:hover:bg-[#001A0A] lg:hover:opacity-80']";
    private final String VERIFY_LOGOUT = "//button[normalize-space()='Đăng nhập']";

    public HomePage(Page page){
        super(page);
    }

    public void closePopupIfPresent() {
        Locator popup = page.locator(CLOSE_POPUP).first();
        try {
            popup.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(3000));
            popup.click(new Locator.ClickOptions().setForce(true));
            page.waitForTimeout(500);
        } catch (PlaywrightException e) {
            System.out.println("Popup not displayed before logout, skip closing.");
        }
    }

    public void openProfileMenu() {
        page.locator(DEPOSIT_BUTTON).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
        page.locator(DEPOSIT_BUTTON).click(new Locator.ClickOptions().setForce(true));
        page.locator(LOGOUT_BUTTON).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
    }

    public void clickLogout() {
        Locator logoutBtn = page.locator(LOGOUT_BUTTON);

        logoutBtn.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(5000));

        logoutBtn.click();

        // Keep the confirm dialog visible for a moment before the next step.
        page.locator(CONFIRM_LOGOUT).waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(5000));
        page.waitForTimeout(3000);
    }

    public void clickConfirmLogout() {
        page.locator(CONFIRM_LOGOUT).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
        page.locator(CONFIRM_LOGOUT).click(new Locator.ClickOptions().setForce(true));
    }

    public void logout(){
//        closePopupIfPresent();
        openProfileMenu();
        clickLogout();
        clickConfirmLogout();
//        verifyLogoutSuccess();
    }

    public boolean isLoggedOut(){
        return page.locator(VERIFY_LOGOUT).isVisible();
    }

    public void verifyLogout() {
        page.locator(VERIFY_LOGOUT).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(10000));
        System.out.println("Logout verified - Sign in button appeared");
    }

    public void verifyLogoutSuccess() {
        verifyLogout();

        if (isLoggedOut()) {
            System.out.println("User successfully logged out");
        } else {
            throw new AssertionError("Logout verification failed - Sign in button not visible");
        }
    }
}

