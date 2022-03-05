package app.api.flow

import app.api.service.UserApiService
import business.account.Account

class UserFlow {

    private final def userApiService = new UserApiService();

    String loginAccount(Account account) {
        def jsonPath = userApiService
                .postLogin(account.email, account.password)
                .jsonPath()
        def token = jsonPath.getString 'token'
        if (token != null) {
            account.token = token
            token
        } else {
            jsonPath.getString 'message'
        }
    }

    String authAccount(Account account) {
        def jsonPath = userApiService
                .getAuth(account.token)
                .jsonPath()
        def token = jsonPath.getString 'token'
        if (token != null) {
            account.token = token
            token
        } else {
            jsonPath.getString 'message'
        }
    }

    void deleteAccount(Account account) {
        userApiService.delete account.id
    }

    Account findAccount(Account account) {
        try {
            userApiService
                    .getFind(account.email)
                    .body()
                    .as Account.class
        } catch (Exception e) {
            e.printStackTrace()
            null
        }
    }
}
