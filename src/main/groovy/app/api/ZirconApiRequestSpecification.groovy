package app.api

import app.ZirconConfig
import app.api.filter.CustomCookieFilter
import app.api.filter.CustomFilter
import io.restassured.builder.RequestSpecBuilder
import io.restassured.http.ContentType

class ZirconApiRequestSpecification {

    private static final def THREAD_LOCAL_INSTANCE = new ThreadLocal<>()
    private static final ZirconConfig ZIRCON_CONFIG = ZirconConfig.config
    private static final String API_URI = ZIRCON_CONFIG.apiUri()
    private static final String API_ENDPOINT = '/api'

    static synchronized def getRequestSpecification() {
        if (THREAD_LOCAL_INSTANCE.get() == null) {
            THREAD_LOCAL_INSTANCE.set(new RequestSpecBuilder()
                    .setBaseUri(API_URI + API_ENDPOINT)
                    .addFilters([new CustomCookieFilter(), new CustomFilter()])
                    .setContentType(ContentType.JSON)
                    .setUrlEncodingEnabled(false)
                    .build())
        }
        return THREAD_LOCAL_INSTANCE.get()
    }
}
