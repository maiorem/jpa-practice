package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 새할인정책을 도입하면서 클라이언트를 수정함.. OCP DIP 원칙 준수X
    //======인터페이스에만 의존하도록 해야 한다.======//
//    private DiscountPolicy discountPolicy;  // <- 그러나 아무 값도 할당이 되어 있지 않아 null임
                                            // 이 문제를 해결하려면 무언가가 OrderServiceImple에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해 주어야 한다.


    // 인터페이스에만 의존하는 설계
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 생성자를 통한 주입(연결)
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
