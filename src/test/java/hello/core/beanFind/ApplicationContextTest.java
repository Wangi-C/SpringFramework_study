package hello.core.beanFind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextTest {

    AnnotationConfigApplicationContext atx = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력")
    void findAllBean() {
        String[] beanDefinitionNames = atx.getBeanDefinitionNames();
        for (String bean : beanDefinitionNames) {
            Object bean1 = atx.getBean(bean);
            System.out.println("name = " + bean1 + "object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력")
    void findApplicationBean() {
        String[] beanDefinitionNames = atx.getBeanDefinitionNames();
        for (String bean : beanDefinitionNames) {
            //빈 하나하나 의 정보
            BeanDefinition beanDefinition = atx.getBeanDefinition(bean);

            //BeanDefinition.ROLE_APPLICATION -> 스프링 외부에서 임의로 만든 빈 객체
//            BeanDefinition.ROLE_INFRASTRUCTURE -> 스프링 내부에서 만든 빈 객체
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean1 = atx.getBean(bean);
                System.out.println("name = " + bean1 + "object = " + bean);
            }
        }
    }
}
