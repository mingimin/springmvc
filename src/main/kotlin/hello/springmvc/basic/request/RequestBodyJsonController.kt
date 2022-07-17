package hello.springmvc.basic.request

import com.fasterxml.jackson.databind.ObjectMapper
import hello.HelloData
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.stereotype.Controller
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import java.nio.charset.StandardCharsets
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * {"username": "hello", "age": 20}
 * content-type: application/json
 */
@Controller
class RequestBodyJsonController(
    private val objectMapper: ObjectMapper
) {
    private val logger: Logger = LoggerFactory.getLogger(RequestHeaderController::class.java)

    @PostMapping("/request-body-json-v1")
    fun requestBodyJsonV1(request: HttpServletRequest, response: HttpServletResponse) {
        val inputStream = request.inputStream
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)

        logger.info("messageBody={}", messageBody)
        val helloData = objectMapper.readValue(messageBody, HelloData::class.java)
        logger.info("username={}, age={}", helloData.username, helloData.age)

        response.writer.write("ok")
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    fun requestBodyJsonV2(@RequestBody messageBody: String): String {
        logger.info("messageBody={}", messageBody)
        val helloData = objectMapper.readValue(messageBody, HelloData::class.java)
        logger.info("username={}, age={}", helloData.username, helloData.age)

        return "ok"
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    fun requestBodyJsonV3(@RequestBody helloData: HelloData): String {
        logger.info("username={}, age={}", helloData.username, helloData.age)

        return "ok"
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    fun requestBodyJsonV4(data: HttpEntity<HelloData>): String {
        val helloData = data.body
        logger.info("username={}, age={}", helloData?.username, helloData?.age)

        return "ok"
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    fun requestBodyJsonV5(@RequestBody helloData: HelloData): HelloData {
        logger.info("username={}, age={}", helloData.username, helloData.age)

        return helloData
    }
}
