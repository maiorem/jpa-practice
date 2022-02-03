package com.maiorem.jpashop.domain;


import com.maiorem.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    //다대다
    @ManyToMany
    @JoinTable(
            name = "category_item",
            joinColumns = @JoinColumn(name = "category"),
            inverseJoinColumns = @JoinColumn(name = "item_id")) //다대일 일대일로 풀어갈 중간 테이블 매핑
    private List<Item> items = new ArrayList<>();

    //카테고리(계층)구조
    //부모
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    //자식
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

//    //---연관관계 메서드--//
//    public void addChildCategory(Category child) {
//        this.child.add(child);
//        child.setParent(this);
//    }
}
