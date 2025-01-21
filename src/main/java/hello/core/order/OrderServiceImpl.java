package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 역할과 구분을 충실하게 분리
    // 다형성을 활용하여, 인터페이스와 구현 객체를 분리하였음.
    // OCP, DIP는 충실히 준수하지 못했음.
    // 클래스 의존 관계를 보면 추상(인터페이스) 뿐만 아니라 객체(구현) 클래스에도 의존하고 있음.
    // 추상(인터페이스) 의존: DiscountPolicy
    // 구체(구현) 클래스: FixDiscountPolicy, RateDiscountPolicy
    // 현재 코드는 기능을 확장해서 변경하면 클라이언트 코드에 영향을 줌 => OCP 위반
    // 그럼 어떻게??
    private DiscountPolicy discountPolicy;
    // 이렇게 하고, 누군가가 OrderServiceImpl 에 DiscountPolicy 를 대신 생성하고 주입해줘야함.

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
