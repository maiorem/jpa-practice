package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)) //@Component가 붙은 모든 클래스를 찾아 빈으로 자동 등록 / 제외 : Configuration 어노테이션(테스트 용 클래스가 없다면 원래는 필요 없음)
public class AutoAppConfig {
// basePackages = ""  ==> 시작되는 패키지 위치 지정. 이 하위 패키지만 컴포넌트스캔을 적용하도록 지정
// basePackageClasses = "" ==> 해당되는 클래스의 패키지를 컴포넌트스캔의 시작위치로 적용하도록 지정. 디폴트는 설정 클래스의 패키지 (설정 정보가 있는 클래스를 때문에 프로젝트 최상단에 두는 것이 보통)
// 일반적으로 서비스, 레포지토리, 컨트롤러 등 어노테이션은 컴포넌트로 자동 등록 되어있음


    // 필드주입 테스트용
//    @Autowired MemberRepository memberRepository;
//    @Autowired
//    DiscountPolicy discountPolicy;
//    @Bean
//    OrderService orderService(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        return new OrderServiceImpl(memberRepository, discountPolicy);
//    }
//
//    // 중복등록 테스트용 (수동 빈 등록)
//    // 수동 빈이 우선권을 가지기 때문에 중복이 나도 자동 빈을 오버라이딩 해버리지만 부트는 중복 오류를 내도록 기본 설정 하고 있음.
////    @Bean(name = "memoryMemberRepository")
//    @Bean
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

}
