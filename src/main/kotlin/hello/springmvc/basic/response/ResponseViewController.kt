package hello.springmvc.basic.response

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class ResponseViewController {

    @RequestMapping("/response-view-v1")
    fun responseViewV1(): ModelAndView {
        return ModelAndView("response/hello").addObject("data", "hello!")
    }

    @RequestMapping("/response-view-v2")
    fun responseViewV2(model: Model): String {
        model.addAttribute("data", "hello!")
        return "response/hello"
    }

    @RequestMapping("/response/hello")
    fun responseViewV3(model: Model) {
        model.addAttribute("data", "hello!")
    }
}
