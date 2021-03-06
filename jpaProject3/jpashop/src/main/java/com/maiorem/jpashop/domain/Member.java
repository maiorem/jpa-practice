package com.maiorem.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

//    @NotEmpty
    private String name;

    @Embedded
    private Address address;

    // 하나의 멤버가 여러개 주문 (일대다)
    @OneToMany(mappedBy = "member") //멤버를 연관관계 주인으로 둠
    @JsonIgnore //json에서 빠지는 정보
    private List<Order> orders = new ArrayList<>();

}
