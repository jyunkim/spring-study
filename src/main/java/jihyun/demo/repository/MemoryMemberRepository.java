package jihyun.demo.repository;

import jihyun.demo.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

//@Repository
public class MemoryMemberRepository implements MemberRepository {

    // 여기선 DB 대신 변수에 저장
    // 모두 같은 변수를 사용할 수 있도록 static으로 선언
    private static Map<Long, Member> store = new HashMap<>();
    // 명시적으로 long형 값이라는 의미
    // 0만 쓰면 묵시적으로 int형을 의미
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // Null일 경우 비어있는 Optional 객체 반환 -> 프론트에서 처리하기 좋음
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // Stream API
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
