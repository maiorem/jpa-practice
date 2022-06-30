package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 새할인정책을 도입하면서 클라이언트를 수정함.. OCP DIP 원칙 준수X
    //======인터페이스에만 의존하도록 해야 한다.======//
//    private DiscountPolicy discountPolicy;  // <- 그러나 아무 값도 할당이 되어 있지 않아 null임
                                            // 이 문제를 해결하려면 무언가가 OrderServiceImple에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해 주어야 한다.


    // 인터페이스에만 의존하는 설계
//    @Autowired // 의존관계를 필드에 바로 주입(권장X - 외부 변경이 불가능함)
    private final MemberRepository memberRepository;
//    @Autowired
    private final DiscountPolicy discountPolicy;


//    // 수정자(세터)를 통한 주입
//    @Autowired(required = false) //true면 주입할 대상이 없을 때 오류가 발생함. false면 주입할 대상이 없어도 동작.
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    // 생성자를 통한 주입(연결). 생성자가 하나면 Autowired 없어도 됨
    // RequiredArgsConstructor 와 중복. 해당 롬복이 있으면 지워도 됨
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }


    // 일반 메서드를 통한 주입
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

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
