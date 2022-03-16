package com.maiorem.jpashop.api;


import com.maiorem.jpashop.domain.Order;
import com.maiorem.jpashop.domain.OrderSearch;
import com.maiorem.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Order 조회
 * 연관관계 매핑
 * * Order -> Member (ManyToOne)
 * * Order -> Delivery (OneToOne)
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    // 연관관계가 있는 객체들이 지연로딩 상태이므로 ByteBuddyIntercepter에서 객체를 생성하여 에러 발생
    // -> Hibernate5 modules 라이브러리 등록 (디폴트 : 지연로딩 무시)
    // 엔티티 직접 노출로 인한 위험문제
    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());

        //전체 강제지연로딩을 끄고 원하는 객체만 지연로딩 하는 법
        for (Order order : all) {
            order.getMember().getName(); // LAZY 강제 초기화
            order.getDelivery().getAddress();
        }
        return all;
    }




}
