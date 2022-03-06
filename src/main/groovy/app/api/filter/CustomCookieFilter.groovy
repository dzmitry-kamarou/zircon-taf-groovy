package app.api.filter

import io.restassured.filter.Filter
import io.restassured.filter.FilterContext
import io.restassured.response.Response
import io.restassured.specification.FilterableRequestSpecification
import io.restassured.specification.FilterableResponseSpecification

class CustomCookieFilter implements Filter {

    private def cookies = [:]

    @Override
    Response filter(FilterableRequestSpecification requestSpec,
                    FilterableResponseSpecification responseSpec,
                    FilterContext ctx) {
        for (def cookie : cookies.entrySet()) {
            requestSpec[cookie.getKey() as String] = cookie.getValue()
        }
        final def response = ctx.next requestSpec, responseSpec
        cookies.putAll response.getCookies()
        return response
    }
}
