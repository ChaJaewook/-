package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemoryMemberRepositoryTest {

    MemberRepository repository=new MemoryMemberRepository();

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
}
