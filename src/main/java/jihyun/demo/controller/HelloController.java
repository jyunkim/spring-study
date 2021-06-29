package jihyun.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    // GET 요청 라우팅
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello");  // 템플릿 변수 설정
        return "hello";  // resources/templates/hello.html render
    }
}
