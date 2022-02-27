package com.maiorem.jpashop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {

    private Long id;

    // ITEM 공통속성
    private String name;
    private int price;
    private int stockQuantity;

    //BOOK 개별 속성
    private String author;
    private String isbn;

}
