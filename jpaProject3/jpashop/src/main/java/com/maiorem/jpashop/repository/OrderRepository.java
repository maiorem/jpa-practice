package com.maiorem.jpashop.repository;

import com.maiorem.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        if(order.getId() == null) {
            em.persist(order); //없는 값이면 신규 등록
        } else {
            em.merge(order); //존재했던 주문이면 병합하여 업데이트
        }
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll() {
        return em.createQuery("select o from Order o", Order.class)
                .getResultList();
    }

}
