package hello.springmvc.basic.request

import hello.HelloData
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
private class RequestParamController(
    private val logger: Logger = LoggerFactory.getLogger(RequestParamController::class.java)
) {

    @RequestMapping("/request-param-v1")
    fun requestParamV1(request: HttpServletRequest, response: HttpServletResponse) {
        val username = request.getParameter("username")
        val age = request.getParameter("age").toInt()
        logger.info("username={}, age={}", username, age)

        response.writer.write("ok")
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    fun requestParamV2(
        @RequestParam("username") memberName: String,
        @RequestParam("age") memberAge: Int
    ): String {
        logger.info("username={}, age={}", memberName, memberAge)

        return "ok"
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    fun requestParamV3(
        @RequestParam username: String,
        @RequestParam age: Int
    ): String {
        logger.info("username={}, age={}", username, age)

        return "ok"
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    fun requestParamV4(username: String, age: Int): String {
        logger.info("username={}, age={}", username, age)

        return "ok"
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    fun requestParamRequired(
        @RequestParam(required = true) username: String,
        @RequestParam(required = false) age: Int
    ): String {
        logger.info("username={}, age={}", username, age)

        return "ok"
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    fun requestParamDefault(
        @RequestParam(required = true, defaultValue = "guest") username: String,
        @RequestParam(required = false, defaultValue = "-1") age: Int
    ): String {
        logger.info("username={}, age={}", username, age)

        return "ok"
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    fun requestParamMap(@RequestParam paramMap: MultiValueMap<String, Any>): String {
        logger.info("username={}, age={}", paramMap["username"], paramMap["age"])

        return "ok"
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    fun modelAttributeV1(@ModelAttribute helloData: HelloData): String {
        logger.info("helloData={}", helloData)

        return "ok"
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    fun modelAttributeV2(helloData: HelloData): String {
        logger.info("helloData={}", helloData)

        return "ok"
    }
}
