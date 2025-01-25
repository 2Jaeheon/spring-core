package hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        System.out.println(
            "memberService -> memberRepository = " + memberService.getMemberRepository());
        System.out.println(
            "orderService -> memberRepository = " + orderService.getMemberRepository());
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);

        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("been = " + bean.getClass());

        // CGLIB를 사용해서 스프링 빈이 존재하면 존재하는 빈을 반환하고, 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진것
        // CGLIB는 AppConfig의 자식 타입이므로 조회가 가능한 것
        // @Configuration 어노테이션을 붙이면 바이트 코드를 조작하는 CGLIB 기술을 사용해서 싱글톤을 보장
        // Configuration을 어노테이션을 사용하지 않으면 의존성을 주입할때 그냥 new 객체를 해서 넣은것과 마찬가지. 스프링 컨테이너가 관리해주지 않음!!
    }
}