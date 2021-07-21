package jihyun.demo.controller;

import jihyun.demo.domain.Member;
import jihyun.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 스프링 빈으로 등록
@Controller
public class MemberController {

    private final MemberService memberService;

    // 의존성 주입
    // 스프링 컨테이너에 등록
    // 스프링 컨테이너의 스프링 빈에 등록되어 있는 서비스 객체를 가져와 인자로 넣어줌 -> 서비스도 스프링 빈으로 등록해줘야 함
    // 생성자가 하나면 어노테이션 생략 가능
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm memberForm) {
        Member member = new Member();
        member.setName(memberForm.getName());

        memberService.join(member);

        // 리다이렉션
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
