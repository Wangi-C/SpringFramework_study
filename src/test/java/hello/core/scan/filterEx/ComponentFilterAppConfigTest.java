package hello.core.scan.filterEx;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterAppConfigTest {
    @Test
    void componentTest() {
        AnnotationConfigApplicationContext atx = new AnnotationConfigApplicationContext(componentFilterAppConfig.class);

        BeanA beanA = atx.getBean("beanA", BeanA.class);
        Assertions.assertThat(beanA).isNotNull();

        org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchBeanDefinitionException.class, () -> atx.getBean("beanB", BeanB.class)
        );
    }

    @Configuration
    @ComponentScan(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
                   excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class componentFilterAppConfig {
    }
}
