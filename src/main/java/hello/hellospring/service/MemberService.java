package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MemberService {
    
    // 회원 리포지토리의 코드가 회원 서비스 코드를 DI 가능
    private final MemberRepository memberRepository;

    @Autowired // 생성자에 사용하면 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입, 생성자가 1개만 있으면 생략 가능
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
