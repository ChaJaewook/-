package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService()
    {
        //이렇게 하면 스프링이 Configuration을 읽고
        //스프링 빈에 호출, 등록을 해준다.
        //Controller는 따로 Bean화 시킬 수 없다.
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository()
    {
        return new MemoryMemberRepository();
    }
}
