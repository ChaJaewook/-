package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private DataSource dataSource;
    private EntityManager em;
    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }


    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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
        //return new MemoryMemberRepository();
        //다른 코드를 손대거나 변경하지 않은 jdbc만 가지고 인터페이스를 확장했다.

        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRespository(em);
    }
}
