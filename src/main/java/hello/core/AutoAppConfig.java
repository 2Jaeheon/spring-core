package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @Component 가 붙은 클래스를 스캔해서 빈으로 등록함.
@ComponentScan(
    // 어디서부터 탐색할지 지정
    // 이게 없으면 모든 자바코드를 다 뒤지기 때문에 시간이 오래걸림
    // basePackages = "hello.core.member",
    // @Configuration 애노테이션이 붙은 설정 정보 제외
    // 권장 방법: 패키지 위치를 지정하지 않고, 설정 정보 클래스 위치를 프로젝트 최상단에 두는 것.
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
/*
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }*/
}
