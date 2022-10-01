package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;
//스프링을 테스트 할때는 ANNOTAION을 하나더 붙여준다.
@SpringBootTest //컨테이너와 테스트를 할께 실행
@Transactional //테스트 케이스에 있으면 완료 후 항상 롤백 실행
//테스트 실행시 트랜젝션을 실행하고 테스트 후 다시 롤백해준다.
class MemberServiceIntegrationTest {

    //MemberService memberService=new MemberService();
    //MemoryMemberRepository memberRepository=new MemoryMemberRepository();

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    //Test는 끝단에 있기에 제일 편한 방법을 사용해야한다.


    @Test
    //@Commit //Transaction처리해놔서 롤백되던게 이제는 적용되게 해준다.
        //테스트코드는 과감하게 한글로 적어 직관성을 높이자
    //빌드시 테스트는 코드에 포함되지 않는다.

    //테스트는 반복할 수 있어야한다.
    //그러나 또 시작하면 기존 데이터로 인해 오류가 발생한다.
    void 회원가입() {
        //given(뭔가가 주어지고)
        Member member=new Member();
        member.setName("spring");

        //when(어떤 상황일때)
        Long saveId=memberService.join(member);

        //then(무언가가 나와야 한다.)
        Member findMember= memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test//중복회원 가입을 통한 예외 처리에 대한 확인용 테스트
    public void 중복_회원_예외()
    {
        //given
        Member member1=new Member();
        member1.setName("spring");

        Member member2=new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //assertThrows(IllegalStateException.class,()->memberService.join(member2));
        IllegalStateException e=assertThrows(IllegalStateException.class,()->memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //예외가 터져야 한다.
        //해석하면 member2에대해 조인을 실시할 건데 앞의 IllegalStateException이 발생해야 한다.
        /*try
        {
            memberService.join(member2);
            fail();
        }
        catch(IllegalStateException e)
        {
            //예외가 터져 성공
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
            //오류메세지를 검증한다.

        }*/



        //then
    }

}