package jihyun.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // GET 요청 라우팅 annotation
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello");  // 템플릿 변수 설정
        return "hello";  // resources/templates/hello.html render
    }

    // Query string(default - required=true)
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("key") String value, Model model) {
        model.addAttribute("key", value);
        return "hello-mvc";
    }

    // HTTP 응답 body에 string 반환
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString() {
        return "Hello Spring";
    }

    // HTTP 응답 body에 객체를 JSON 형태로 반환
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    // 내부 클래스
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
