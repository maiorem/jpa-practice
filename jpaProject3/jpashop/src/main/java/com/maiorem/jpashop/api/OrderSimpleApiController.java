package com.maiorem.jpashop.api;


import com.maiorem.jpashop.domain.Address;
import com.maiorem.jpashop.domain.Order;
import com.maiorem.jpashop.domain.OrderSearch;
import com.maiorem.jpashop.domain.OrderStatus;
import com.maiorem.jpashop.dto.OrderSimpleQueryDto;
import com.maiorem.jpashop.repository.OrderRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    // -> 지연로딩의 문제를 피하려고 즉시로딩을 하면 연관관계 매핑이 필요 없는 경우에도 무조건 데이터 조회를 하기 때문에 반드시 성능 문제가 발생!
    // -> Hibernate5 modules 라이브러리 등록 (디폴트 : 지연로딩 무시)
    // 엔티티 직접 노출로 인한 위험문제
    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());

        //전체 강제지연로딩을 끄고 원하는 객체만 지연로딩 하는 법
        for (Order order : all) {
            order.getMember().getName(); // LAZY 강제 초기화
            order.getDelivery().getAddress(); // LAZY 강제 초기화
        }
        return all;
    }

    // 엔티티 노출을 피하기 위해 DTO로 변환
    // 쿼리가 너무 많이 나가기 때문에 성능 문제가 있음
    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2(){
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());
        return result;
    }


    // 패치조인으로 쿼리를 한번으로 줄임
    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3(){
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());
        return result;
    }

    // 패치조인으로 튜닝된 쿼리를 바로 DTO로 전환
    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4(){
        return orderRepository.findOrderDto();
    }


    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName(); //LAZY 초기화
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress(); //LAZY 초기화
        }
    }




}
