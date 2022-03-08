package hello.core.singletone;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletoneTest {
    @Test
    @DisplayName("스프링 없는 순수한 di컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        MemberService memberService1 = appConfig.memberService();

        MemberService memberService2 = appConfig.memberService();

        //참조 값이 다른 것 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletoneServiceTest() {
        SingletoneService singletoneService1 = SingletoneService.getInstance();

        SingletoneService singletoneService = SingletoneService.getInstance();

        System.out.println("singletoneService = " + singletoneService);
        System.out.println("singletoneService1 = " + singletoneService1);

        assertThat(singletoneService1).isSameAs(singletoneService);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        AnnotationConfigApplicationContext atx = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = atx.getBean("memberService", MemberService.class);
        MemberService memberService2 = atx.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);
    }
}
