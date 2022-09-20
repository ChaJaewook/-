package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    //회원이 저장된 후 반환된다.

    Optional<Member> findById(Long id);
    //ID로 회원을 찾는다.
    //OPTIONAL은 자바8에 들어간 기능이다.

    Optional<Member> findByName(String name);

    List<Member> findAll();
    //모든회원 리스트 반환


}
