package com.maiorem;

import javax.persistence.*;

@Entity
public class MemberProduct {

    @Id @GeneratedValue
    private Long id;


    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member2 member2;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

}
