package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//JpaRepository가 기본적 메소드를 다 제공해 준다.
//CRUD

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
    //규칙 name
    //이렇게 쓰면 실질직으로는 select m from Member m where m.name=?이 된다.

    //Spring Data JPA가 알아서 구현체를 만들어서 BEAN에 등록한다.
    //가져다가 쓰면 된다.

    //이름으로 찾고 이런 개인적 로직은 사용이 불가능하다.
    //공통할 수 없는 메소드다.


    //복잡한 동적 쿼리는 querydsl이라는 라이브러리를 사용한다.
    //네이티브 쿼리도 사용이 가능하다.

}
