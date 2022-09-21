package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository=new MemoryMemberRepository();
    @AfterEach
    //메소드가 끝날 때마다 자동으로 실행
    public void afterEach()
    {
        repository.clearStore();
    }
    @Test //실행가능하다.
    public void save()
    {
        Member member=new Member();
        member.setName("spring");
        //이름을지정하고 저장한다.

        repository.save(member);

        Member result=repository.findById(member.getId()).get();
        //멤버랑 리절트를 비교한다.
        //다르면 밑의 테스트 창에 오류가 난다.
        //Assertions.assertEquals(result,member);
        Assertions.assertThat(member).isEqualTo(result);
        //멤버가 result가 같으면

    }

    @Test
    public void findByName()
    {
        Member member1=new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result=repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll()
    {
        Member member1 =new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 =new Member();
        member1.setName("spring2");
        repository.save(member2);

        List<Member> result=repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }


    // 테스트가 실패가뜬다.
    // 테스트시에는 순서가 없다.
    // FINDALL이 제일먼저 실행이 된다.
    // 그렇기에 에러가 난다.
    // 데이터 클리어링이 필요하다.
}
