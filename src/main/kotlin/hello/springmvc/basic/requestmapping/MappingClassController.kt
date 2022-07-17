package hello.springmvc.basic.requestmapping

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mapping/users")
class MappingClassController(
    private val logger: Logger = LoggerFactory.getLogger(MappingClassController::class.java)
) {
    /**
     * 회원 목록 조회: GET `/users`
     * 회원 등록: POST `/users`
     * 회원 조회: GET `/users/{userId}
     * 회원 수정: PATCH `/users/{userId}
     * 회원 삭제: DELETE `/users/{userId}
     */

    @GetMapping
    fun user(): String {
        return "get users"
    }

    @PostMapping
    fun addUser(): String {
        return "post user"
    }

    @GetMapping("/{userId}")
    fun findUser(@PathVariable userId: String): String {
        return "get userId=$userId"
    }

    @PatchMapping("/{userId}")
    fun updateUser(@PathVariable userId: String): String {
        return "update userId=$userId"
    }

    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable userId: String): String {
        return "delete userId=$userId"
    }
}
