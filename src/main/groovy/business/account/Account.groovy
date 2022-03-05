package business.account

import business.role.Role
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import util.TimeUtil
import java.time.LocalDateTime

class Account {
    long id
    String email
    String password
    Role role
    String token
    @JsonDeserialize(using = TimeUtil.class)
    LocalDateTime createdAt
    @JsonDeserialize(using = TimeUtil.class)
    LocalDateTime updatedAt
}
