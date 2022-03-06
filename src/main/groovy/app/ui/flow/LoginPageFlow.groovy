package app.ui.flow

import app.ui.page.LoginPage
import business.account.Account

class LoginPageFlow {

    private final LoginPage loginPage

    LoginPageFlow() {
        loginPage = new LoginPage()
    }

    DashboardPageFlow login(Account account) {
        loginPage.fillEmailField account.getEmail()
        loginPage.fillPasswordField account.getPassword()
        loginPage.clickLoginButton()
        new DashboardPageFlow()
    }

    LoginPageFlow registration() {
        loginPage.clickRegistrationLink()
        this
    }

    void register(Account account) {
        loginPage.fillEmailField account.getEmail()
        loginPage.fillPasswordField account.getPassword()
        loginPage.clickRegisterButton()
    }
}
