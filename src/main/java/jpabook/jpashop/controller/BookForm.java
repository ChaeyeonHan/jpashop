package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {
    private Long id;  // 상품 수정을 위한 id값

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;
}
