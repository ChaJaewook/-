package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    //서비스라 이름이 조인, findMembers 등 이름이
    //비즈니스에 가까워진다.
    //서비스는 비즈니스 처리
    private final MemberRepository memberRepository=new MemoryMemberRepository();

    //회원가입
    public Long join(Member member)
    {
        //optional은 바로 반환하는걸 권장하지 않는다.
        //Optional<Member> result=memberRepository.findByName(member.getName());
        //ifPresent==> null이 아니라 값이 있다면
        //Optional이기 때문에 가능
        /*result.ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/
        //위와 같은 식을
        //밑으로 줄여서 사용한다.
        //직접적으로 optional을 받는건 좋지 않기 때문이다.

        validateDuplicateMember(member);//중복회원 검증

        memberRepository.save(member);
        return member.getId();
        //같은 이름의 회원이 있을 수 없다.
        //중복에 대한 처리 필요
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m-> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    // 전체회원 조회
    public List<Member> findMembers()
    {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId)
    {
        return memberRepository.findById(memberId);
    }
}