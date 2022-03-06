package app.api.flow

import app.api.service.UserApiService
import business.account.Account
import org.json.JSONObject

class UserFlow {

    private static final String TOKEN_NODE = 'token'
    private static final String MESSAGE_NODE = 'message'
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

    String registerAccount(Account account) {
        def jsonPath = userApiService
                .postRegister(account.email, account.password)
                .jsonPath()
        def token = jsonPath.getString TOKEN_NODE
        if (token != null) {
            account.id = new JSONObject(new String(Base64
                    .getDecoder()
                    .decode(token.split('\\.')[1])))
                    .getLong('id')
            token
        } else {
            jsonPath.getString MESSAGE_NODE
        }
    }
}
