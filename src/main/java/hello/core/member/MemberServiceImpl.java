package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // 실제로는 구현체에도 의존하고 있음. 추상화에도 의존하며, 구현체에도 의존하고 있음. 즉, DIP (의존성 역전 원칙) 를 위반하고 있음.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
