package app.ui.flow

import app.ui.page.DashboardPage

class DashboardPageFlow {

    private final DashboardPage dashboardPage

    DashboardPageFlow() {
        dashboardPage = new DashboardPage()
    }

    boolean exitButtonVisible() {
        dashboardPage.isExitButtonVisible()
    }

    WelcomePageFlow exit() {
        dashboardPage.clickExitButton()
        new WelcomePageFlow()
    }
}
