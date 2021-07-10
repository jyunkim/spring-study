package jihyun.demo.service;

import jihyun.demo.domain.Member;
import jihyun.demo.repository.MemberRepository;
import jihyun.demo.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    // 의존성 주입
    // 객체를 직접 생성하지 않고 외부에서 넣어줌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join(Member member) {
        // 중복 회원 검증
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 같은 이름이 있으면 안됨
        // 반환값이 null이 아닐 경우 예외
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
