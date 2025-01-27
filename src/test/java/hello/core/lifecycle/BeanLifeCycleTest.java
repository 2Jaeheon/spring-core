package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        // LifeCycleConfig를 사용하여 스프링 컨텍스트 초기화
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(
            LifeCycleConfig.class);

        // NetworkClient Bean 가져오기
        NetworkClient client = ac.getBean(NetworkClient.class);

        // 스프링 컨텍스트 종료
        ac.close();
    }

    @Configuration
    @ComponentScan
    public static class LifeCycleConfig {

    }
}
