package hello.springmvc.basic

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LogTestController(
    private val logger: Logger = LoggerFactory.getLogger(LogTestController::class.java)
) {

    @RequestMapping("/log-test")
    fun logTest(): String {
        val name = "Spring"

        logger.trace("trace log={}", name)
        logger.debug("debug log={}", name)
        logger.info("info log={}", name)
        logger.warn("warn log={}", name)
        logger.error("error log={}", name)

        return "ok"
    }
}
