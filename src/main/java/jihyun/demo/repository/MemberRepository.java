package jihyun.demo.repository;

import jihyun.demo.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    // public abstract 생략 가능
    Member save(Member member);
    // T 타입의 객체를 포장해주는 wrapper class
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
