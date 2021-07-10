package jihyun.demo.repository;

import jihyun.demo.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// 밖에서 쓰이지 않기 때문에 public x
class MemoryMemberRepositoryTest {

    // import 필요 x
    // 타입을 interface로 할 경우 clearStore 메서드 사용 불가
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 하나의 테스트가 끝날 때마다 repository를 비워줘야 함 -> 의존성 제거
    // 각 메서드 실행이 끝날 때마다 실행됨
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("kim");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // actual = result, expect = member
//        Assertions.assertEquals(member, result);
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName() {
        Member member = new Member();
        member.setName("kim");
        repository.save(member);

        Member result = repository.findByName("kim").get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("kim");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("lee");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
