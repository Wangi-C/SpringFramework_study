package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//필드의 final로 선언된 필드에 해당되는 생성자를 만들어준다.
public class OrderServiceImpl implements OrderService {
    //DIP위반 - 항상 추상화에 의존해라, interface에 의존

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    //불변 -> setter와 같은 수정메소드가 없는것

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
        //@Autowired : 같은 타입의 빈이 여러개 일 때 파라미터명, 필드명 을 보고 주입을 해준다.
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
