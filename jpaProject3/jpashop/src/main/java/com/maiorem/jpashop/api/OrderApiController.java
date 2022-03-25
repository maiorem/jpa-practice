package com.maiorem.jpashop.api;

import com.maiorem.jpashop.domain.*;
import com.maiorem.jpashop.repository.OrderRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;

    //엔티티 노출
    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();
            //OneToMany 관계 추가
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.stream().forEach(o -> o.getItem().getId());
        }
        return all;
    }

    //엔티티를 DTO로 변환
    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<OrderDto> collect = orders.stream()
                .map(order -> new OrderDto(order))
                .collect(Collectors.toList());
        return collect;

    }

    @Data
    static class OrderDto {

        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItems;

        public OrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
           // orderitem도 엔티티를 dto로 변환해야
//            order.getOrderItems().stream().forEach(o -> o.getItem().getName());
//            orderItems = order.getOrderItems();
            orderItems = order.getOrderItems().stream()
                    .map(orderItem -> new OrderItemDto(orderItem))
                    .collect(Collectors.toList());
        }

    }

    @Data
    static class OrderItemDto {

        private String itemName;
        private int orderPrice;
        private int count;

        public OrderItemDto(OrderItem orderItem) {
            itemName = orderItem.getItem().getName();
            orderPrice = orderItem.getOrderPrice();
            count = orderItem.getCount();
        }

    }

    // 일대다 컬렉션 페치조인 (distinct로 중복조회 방지)
    // 단점 : 페이징 불가능 (limit 쿼리가 없음) => 메모리에 페치조인과 페이징이 같이 적용됨. out of memory!
    // 컬렉션 페치조인은 1개만 사용 가능. 둘 이상 사용하면 데이터가 부정합하게 조회될 수 있음
    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithItem();
        
        List<OrderDto> collect = orders.stream()
                .map(order -> new OrderDto(order))
                .collect(Collectors.toList());
        return collect;
    }


    /**
     * 페이징 한계돌파 방법
     * 1. ToOne 관계는 전부 페치조인을 건다. (ToOne은 페치조인이 이어져도 데이터가 증가하지 않으므로 페이징에 영향을 주지 않는다)
     * 2. application.yml에서 default_batch_fetch_size 옵션 : where ~ in 쿼리의 갯수 -> 글로벌 적용
     *  => 디테일하게 적용하고 싶으면 해당 엔티티 참조에 @BatchSize를 넣어주기
     */
    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> ordersV3_1(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "100") int limit) {
        //ToOne 관계에 걸린 데이터를 페치조인으로 가져 옴
        //파람으로 가져온 firstresult와 maxresult로 페이징
        List<Order> orders = orderRepository.findAllWithMemberDelivery(offset, limit);

        List<OrderDto> collect = orders.stream()
                .map(order -> new OrderDto(order))
                .collect(Collectors.toList());
        return collect;
    }

}
