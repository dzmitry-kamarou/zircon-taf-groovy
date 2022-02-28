package app.api.flow

import app.api.service.UserApiService
import business.account.Account
import io.restassured.path.json.JsonPath

class UserFlow {

    private final def userApiService = new UserApiService();

    String loginAccount(Account account) {
        def jsonPath = userApiService
                .postLogin(account.getEmail(), account.getPassword())
                .jsonPath()
        def token = jsonPath.getString('token')
        if (token != null) {
            account.token = token
            return token
        } else {
            return jsonPath.getString('message')
        }
    }

    String authAccount(Account account) {
        JsonPath jsonPath = userApiService
                .getAuth(account.getToken())
                .jsonPath()
        String token = jsonPath.getString 'token'
        if (token != null) {
            account.setToken(token)
            return token
        } else {
            return jsonPath.getString('message')
        }
    }
}
