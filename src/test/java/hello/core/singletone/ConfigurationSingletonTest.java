package hello.core.singletone;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

        public class ConfigurationSingletonTest {
            @Test
            void configurationTest() {
                AnnotationConfigApplicationContext atx = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = atx.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = atx.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = atx.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        AnnotationConfigApplicationContext atx = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = atx.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
    }
}
