package hello.core.singletone;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext atx = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = atx.getBean(StatefulService.class);
        StatefulService statefulService2 = atx.getBean(StatefulService.class);

        //ThreadA : A사용자가 10000원을 주문
        statefulService1.order("userA", 10000);
        //ThreadB : B 사용자가 20000원을 주문
        statefulService2.order("userB", 20000);

        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    @Configuration
    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
