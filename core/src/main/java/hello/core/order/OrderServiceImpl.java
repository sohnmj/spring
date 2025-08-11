package hello.core.order;

import hello.core.Disccount.DiscountPolicy;
import hello.core.Disccount.FixDiscountPolicy;
import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// lombok이 알아서 생성자를 만들어준다. 굉장히 편리하다.
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private  final MemberRepository memberRepository;

    private final DiscountPolicy discountPolicy;
    // 클래스에 생성자가 하나일떄는 @Autowired 생략가능
    public OrderServiceImpl( MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

    @Override
    public Order createorder(Long memberId, String itemName, int itemPrice) {
        Member member=memberRepository.findById(memberId);
        int discount = discountPolicy.discount(member, itemPrice);
        return new Order(discount,itemPrice,memberId,itemName);
    }

    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
