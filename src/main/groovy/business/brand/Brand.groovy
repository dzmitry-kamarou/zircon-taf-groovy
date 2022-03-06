package business.brand

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import util.TimeUtil

import java.time.LocalDateTime

class Brand {

    long id
    String name
    @JsonDeserialize(using = TimeUtil.class)
    LocalDateTime createdAt
    @JsonDeserialize(using = TimeUtil.class)
    LocalDateTime updatedAt
}
