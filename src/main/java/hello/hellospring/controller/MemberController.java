package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    //spring container가 @Controller만 붙여도 멤버 컨트롤로 객체를 생성해 관리를 한다.
    //bean이 관리된다.
    private final MemberService memberService;

    //의존관계를 주입한다. 스프링이 해준다.
    @Autowired //멤버 서비스를 스프링 컨테이너가 자동을 연겨해준다.(컴포넌트 스캔 방식)
    //private MemberService memberService ==>필드주입방법
    //서비스 안에 Component라는 annotation이 있다.
    //Dependency Injection을 통한방법

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //private final MemberService memberService=new MemberService();

    //new를 하면 멤버 컨트롤러 뿐만 아니라 다른 컨트롤러 들이 MemberService를 가져다 쓸 수 있다.
    //그러나 별 기능이 없어 하나만 생성해 공유해도 편한다.

    //스프링 컨테이너에 등록을 하자.

    @GetMapping("/members/new")
    public String createForm()
    {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) //MemberForm에 spring이라는 값이 들어온다.
    {
        Member member=new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
        //회원가입이 끝났기에 홈 화면으로 보낸다.

        //회원가입 클릭시 /members/new로 들어온다.,

    }

    @GetMapping("/members")
    public String list(Model model)
    {
        List<Member> members=memberService.findMembers();
        model.addAttribute("members",members);

        return "members/memberList";
    }


}
