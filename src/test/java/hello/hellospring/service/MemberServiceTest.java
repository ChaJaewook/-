package hello.hellospring.service;

import hello.hellospring.domain.Member;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.repository.MemoryMemberRepositoryTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //MemberService memberService=new MemberService();
    //MemoryMemberRepository memberRepository=new MemoryMemberRepository();

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //실행하기전에 실행되는 부분
    @BeforeEach
    public void beforeEach()
    {
        memberRepository=new MemoryMemberRepository();
        memberService=new MemberService(memberRepository);
        //Dependency Injection은 직접 생성하지 않지만 사용할 수 잇다. MemberService 기준

    }
    @AfterEach
    //메소드가 끝날 때마다 자동으로 실행
    public void afterEach()
    {
        memberRepository.clearStore();
        // 메모리 클리어

    }

    @Test
    //테스트코드는 과감하게 한글로 적어 직관성을 높이자
    //빌드시 테스트는 코드에 포함되지 않는다.

    void 회원가입() {
        //given(뭔가가 주어지고)
        Member member=new Member();
        member.setName("hello");

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

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}