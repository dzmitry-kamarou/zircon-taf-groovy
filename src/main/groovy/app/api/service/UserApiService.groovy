package app.api.service

import groovy.util.logging.Slf4j
import io.restassured.response.Response

@Slf4j
class UserApiService extends BaseApiService {

    private static final def USER_ENDPOINT = '/user'

    Response postLogin(def email, def password) {
        log.info 'POST LOGIN ACCOUNT'
        def payload = [:]
        payload['email'] = email
        payload['password'] = password
        zirconSpecification()
                .basePath(USER_ENDPOINT)
                .body(payload)
                .when()
                .post('/login')
    }

    Response getAuth(String token) {
        log.info 'GET AUTH'
        zirconSpecification()
                .headers('Authorization', "Bearer ${token}")
                .basePath(USER_ENDPOINT)
                .when()
                .get('/auth')
    }

    Response delete(long id) {
        log.info 'DELETE ACCOUNT BY ID'
        zirconSpecification()
                .basePath(USER_ENDPOINT)
                .pathParam('id', id)
                .when()
                .delete('/{id}')
    }

    Response getFind(String email) {
        log.info 'GET FIND ACCOUNT'
        zirconSpecification()
                .basePath(USER_ENDPOINT)
                .queryParam('email', email)
                .when()
                .get('/find')
    }
}
