package com.maiorem.jpashop.domain.item;

import com.maiorem.jpashop.domain.Category;
import com.maiorem.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@BatchSize(size = 100) // 컬렉션이 아닌 경우엔 엔티티에 적용
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //상속 타입 : 싱글 테이블 전략
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;
    private int stockQuantity;

    //다대다 연관관계
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//
    /*
    *재고수량 증가
    */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /*
     *재고수량 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

}
