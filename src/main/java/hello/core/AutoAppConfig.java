package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //default(basePackages) @Component스캔 대상 위치 -> AutoAppConfig클래스가 위치한 hello.core 패키지의 모든 클래스를 스캔한다.
        //권장 : 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION
                                               , classes = Configuration.class)
        //AppConfig 스프링 java설정파일을 제외
        //Configuration 도 @Component의 대상
)
public class AutoAppConfig {
//    @Bean(name = "memoryMemberRepository")
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
