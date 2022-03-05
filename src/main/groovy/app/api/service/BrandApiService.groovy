package app.api.service

import groovy.util.logging.Slf4j
import io.restassured.response.Response

@Slf4j
class BrandApiService extends BaseApiService {

    private static final String BRAND_ENDPOINT = '/brand'

    Response postCreate(String name) {
        log.info 'POST CREATE BRAND'
        zirconSpecification()
                .basePath(BRAND_ENDPOINT)
                .body([name: name])
                .when()
                .post()
    }

    Response getAll() {
        log.info 'GET ALL BRANDS'
        zirconSpecification()
                .basePath(BRAND_ENDPOINT)
                .when()
                .get()
    }

    Response delete(long id) {
        log.info 'DELETE BRAND BY ID'
        return zirconSpecification()
                .basePath(BRAND_ENDPOINT)
                .pathParam('id', id)
                .when()
                .delete('/{id}')
    }

    Response getFind(String name) {
        log.info 'GET FIND BRAND'
        return zirconSpecification()
                .urlEncodingEnabled(true)
                .basePath(BRAND_ENDPOINT)
                .queryParam('name', name)
                .when()
                .get('/find')
    }
}
