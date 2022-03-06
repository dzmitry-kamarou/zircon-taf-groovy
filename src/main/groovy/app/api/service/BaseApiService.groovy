package app.api.service

import static app.api.ZirconApiRequestSpecification.getRequestSpecification
import static io.restassured.RestAssured.given

class BaseApiService {

    protected def zirconSpecification() {
        return given().spec(getRequestSpecification())
    }
}
