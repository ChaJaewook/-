package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRespository implements MemberRepository{
    //jpa는 entityManager로 작동한다.

    private final EntityManager em;

    public JpaMemberRespository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        //persist 영속하다.
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member= em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    //단건이 아니기에 쿼리가 필요하다.
    public Optional<Member> findByName(String name) {
        List<Member> result=em.createQuery("select m from Member m where m.name=:name",Member.class)
                .setParameter("name",name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m",Member.class).getResultList();
        //인라인화 했다.
        //라인들이 합쳐진다.
        //테이블 대상이 아닌 객체 대상으로 쿼리를 날린다.

    }
}
