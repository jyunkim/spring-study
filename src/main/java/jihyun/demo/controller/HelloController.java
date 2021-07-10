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

    // Inner class(Non-static nested class)는 항상 외부 인스턴스에 대한 참조가 유지됨
    // 외부 클래스의 자원을 직접 사용할 수 있지만,
    // 메모리에 대한 참조가 유지되어 메모리 누수가 발생할 수 있고,
    // 항상 외부 인스턴스의 참조를 통해야 하므로 성능 상 비효율적
    // 따라서 외부 인스턴스에 대한 참조가 필요하지 않다면 static nested class로 만드는 것이 나음
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
