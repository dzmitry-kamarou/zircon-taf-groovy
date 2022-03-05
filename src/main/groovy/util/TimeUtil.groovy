package util

import com.fasterxml.jackson.core.JacksonException
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.github.sisyphsu.dateparser.DateParserUtils
import java.time.LocalDateTime

class TimeUtil extends JsonDeserializer<LocalDateTime> {

    @Override
    LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException,
            JacksonException {
        try {
            DateParserUtils.parseDateTime parser.getText()
        } catch (Exception e) {
            e.printStackTrace()
        }
        null
    }
}
