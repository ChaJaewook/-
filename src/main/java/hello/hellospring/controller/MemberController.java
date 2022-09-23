package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    //spring container가 @Controller만 붙여도 멤버 컨트롤로 객체를 생성해 관리를 한다.
    //bean이 관리된다.
    private final MemberService memberService;

    //의존관계를 주입한다. 스프링이 해준다.
    @Autowired //멤버 서비스를 스프링 컨테이너가 자동을 연겨해준다.(컴포넌트 스캔 방식)
    //서비스 안에 Component라는 annotation이 있다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //private final MemberService memberService=new MemberService();

    //new를 하면 멤버 컨트롤러 뿐만 아니라 다른 컨트롤러 들이 MemberService를 가져다 쓸 수 있다.
    //그러나 별 기능이 없어 하나만 생성해 공유해도 편한다.

    //스프링 컨테이너에 등록을 하자.


}
