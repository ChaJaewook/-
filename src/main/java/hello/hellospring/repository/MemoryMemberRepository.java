package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store=new HashMap<>(); //동시성 문제가 있을 수 있다
    //어딘가에 저장시 사용
    // KEY는 회원 아이디
    // 값은 멤버

    private static long sequence=0L;//키 값 생성을 위한다.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        //ID세팅
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //STORE에서 꺼낸다.
        return Optional.ofNullable(store.get(id));
        //만약 결과가 Null인걸 대비해 optinal로 감싸준다.
        //클라이언트에서 뭔가를 할 수 있다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 람다식으로한다,.
        // loop로 돌린다.
        // filter로 한다.
        // 파라미터이름고 같을때만 필터링해서 리턴한다.
        // 끝까지 돌렸는데 없으면 optional로 감싸져 온다.
        return store.values().stream()
                .filter(member->member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        //자바 실무시 List많이 쓴다.
        return new ArrayList<>(store.values());

    }

    public void clearStore()
    {
        store.clear();
    }
}
