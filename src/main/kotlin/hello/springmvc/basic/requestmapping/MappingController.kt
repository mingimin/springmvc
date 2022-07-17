package hello.springmvc.basic.requestmapping

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class MappingController(
    private val logger: Logger = LoggerFactory.getLogger(MappingController::class.java)
) {

    @RequestMapping("/hello-basic")
    fun helloBasic(): String {
        logger.info("basicBasic")
        return "ok"
    }

    @RequestMapping(value = ["/mapping-get-v1"], method = [RequestMethod.GET])
    fun mappingGetV1(): String {
        logger.info("mappingGetV1")
        return "ok"
    }

    /**
     * 편리한 축약 애노테이션 (코드보기)
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping(value = ["/mapping-get-v2"])
    fun mappingGetV2(): String {
        logger.info("mappingGetV2")
        return "ok"
    }

    /**
     * PathVariable 사용
     * 변수명이 같으면 생략 가능
     * @PathVariable("userId") String userId -> @PathVariable userId
     */
    @GetMapping("/mapping/{userId}")
    fun mappingPath(@PathVariable("userId") data: String): String {
        logger.info("mappingPath userId={}", data)
        return "ok"
    }

    /**
     * PathVariable 사용 다중
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    fun mappingPath(@PathVariable userId: String, @PathVariable orderId: Long): String {
        logger.info("mappingPath userId={}, orderId={}", userId, orderId)
        return "ok"
    }

    /**
     * 파라미터로 추가 매핑
     * params="mode"
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug"
     * params={"mode=debug", "data=good"}
     */
    @GetMapping(value = ["/mapping-param"], params = ["mode=debug"])
    fun mappingParam(): String {
        logger.info("mappingParam")
        return "ok"
    }

    /**
     * 특정 헤더로 추가 매핑
     * headers="mode"
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug"
     */
    @GetMapping(value = ["/mapping-header"], headers = ["mode=debug"])
    fun mappingHeader(): String {
        logger.info("mappingHeader")
        return "ok"
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/\*"
     * consumes="*\/\*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = ["/mapping-consume"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun mappingConsumes(): String {
        logger.info("mappingConsumes")
        return "ok"
    }

    /**
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/\*
     * produces = "*\/\*"
     */
    @PostMapping(value = ["/mapping-produce"], produces = [MediaType.TEXT_HTML_VALUE])
    fun mappingProduce(): String {
        logger.info("mappingProduces")
        return "ok"
    }
}
