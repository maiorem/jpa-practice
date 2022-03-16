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

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        return all;
    }




}
