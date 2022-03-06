package app.api.filter

import groovy.util.logging.Slf4j
import io.restassured.filter.Filter
import io.restassured.filter.FilterContext
import io.restassured.response.Response
import io.restassured.specification.FilterableRequestSpecification
import io.restassured.specification.FilterableResponseSpecification
import org.json.JSONObject

@Slf4j
class CustomFilter implements Filter {

    private static final int INDENTATION_VALUE = 4

    @Override
    Response filter(FilterableRequestSpecification requestSpec,
                    FilterableResponseSpecification responseSpec,
                    FilterContext ctx) {
        def body = requestSpec.getBody()
        def bodyString = body == null ? '' : new JSONObject(body).toString(INDENTATION_VALUE)
        def requestText = "[REQUEST]\n${requestSpec.getMethod()} ${requestSpec.getURI()}\nHeaders:\n${requestSpec.getHeaders()}\nBody: '${bodyString}'"
        log.debug requestText
        def response = ctx.next requestSpec, responseSpec
        bodyString = response.getBody() == null ? '' : response.getBody().prettyPrint()
        def responseText = "[RESPONSE]\n${response.getStatusCode()}\nBody: '${bodyString}'"
        log.debug responseText
        return response
    }
}
