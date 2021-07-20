package jihyun.demo.controller;

import jihyun.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// 스프링 빈으로 등록
@Controller
public class MemberController {

    private final MemberService memberService;

    // 의존성 주입
    // 스프링 컨테이너에 등록
    // 스프링 컨테이너의 스프링 빈에 등록되어 있는 서비스 객체를 가져와 인자로 넣어줌 -> 서비스도 스프링 빈으로 등록해줘야 함
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
