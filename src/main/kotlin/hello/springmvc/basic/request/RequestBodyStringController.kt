package hello.springmvc.basic.request

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.stereotype.Controller
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import java.io.InputStream
import java.io.Writer
import java.nio.charset.StandardCharsets
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class RequestBodyStringController(
    private val logger: Logger = LoggerFactory.getLogger(RequestHeaderController::class.java)
) {

    @PostMapping("/request-body-string-v1")
    fun requestBodyString(request: HttpServletRequest, response: HttpServletResponse) {
        val inputStream = request.inputStream
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)

        logger.info("messageBody={}", messageBody)

        response.writer.write("ok")
    }

    @PostMapping("/request-body-string-v2")
    fun requestBodyStringV2(inputStream: InputStream, responseWriter: Writer) {
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)

        logger.info("messageBody={}", messageBody)

        responseWriter.write("ok")
    }

    @PostMapping("/request-body-string-v3")
    fun requestBodyStringV3(httpEntity: HttpEntity<String>): HttpEntity<String> {
        logger.info("messageBody={}", httpEntity.body)

        return HttpEntity("ok")
    }

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    fun requestBodyStringV4(@RequestBody messageBody: String): String {
        logger.info("messageBody={}", messageBody)

        return "ok"
    }
}
