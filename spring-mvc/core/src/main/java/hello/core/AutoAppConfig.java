package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)) //@Component가 붙은 모든 클래스를 찾아 빈으로 자동 등록 / 제외 : Configuration 어노테이션(테스트 용 클래스가 없다면 원래는 필요 없음)
public class AutoAppConfig {

}
