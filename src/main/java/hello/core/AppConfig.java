package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    // 설계에 관해 역할들이 구성 정보에 그대로 드러나야함.
    // 역할과 구현이 한 눈에 들어와야 함.

    /*
     * new MemberRepository() -> memberRepository() 로 변경
     * 애플리케이션의 전체 구성에 관해서 빠르게 파악이 가능하다는 장점이 존재함.
     * */

    // MemberService 역할
    public MemberService memberService() {
        // 구현에서는 Imple을 쓸거야!
        return new MemberServiceImpl(memberRepository());
    }

    // MemberRepository 역할
    private static MemberRepository memberRepository() {
        // MemoryMemberRepository를 쓸거야!
        return new MemoryMemberRepository();
    }

    // OrderService 역할
    public OrderService orderService() {
        // OrderServiceImpl을 쓸거야!
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // DiscountPolicy 역할
    public DiscountPolicy discountPolicy() {
        // FixDiscountPolicy를 쓸거야!
        // return new FixDiscountPolicy();

        // RateDiscountPolicy를 쓰는걸로 바꾸자!!
        return new RateDiscountPolicy();
    }
}
