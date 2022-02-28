package app.api.service

import groovy.util.logging.Slf4j
import io.restassured.response.Response

@Slf4j
class UserApiService extends BaseApiService {

    private static final String USER_ENDPOINT = '/user'

    Response postLogin(def email, def password) {
        log.info 'POST LOGIN ACCOUNT'
        def payload = [:]
        payload['email'] = email
        payload['password'] = password
        return zirconSpecification()
                .basePath(USER_ENDPOINT)
                .body(payload)
                .when()
                .post('/login')
    }

    Response getAuth(String token) {
        log.info 'GET AUTH'
        return zirconSpecification()
                .headers('Authorization', "Bearer ${token}")
                .basePath(USER_ENDPOINT)
                .when()
                .get('/auth')
    }
}
