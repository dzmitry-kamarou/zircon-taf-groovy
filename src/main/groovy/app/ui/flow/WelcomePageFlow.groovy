package app.ui.flow

import app.ui.page.WelcomePage

class WelcomePageFlow {

    private final WelcomePage welcomePage

    WelcomePageFlow() {
        welcomePage = new WelcomePage()
    }

    LoginPageFlow login() {
        welcomePage.clickLoginButton()
        return new LoginPageFlow()
    }
}
