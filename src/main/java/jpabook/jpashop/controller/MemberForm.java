package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {  // DTO

    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;  // 이름은 필수 입력요소. 값이 비어있으면 오류가 발생한다
    private String city;
    private String street;
    private String zipcode;

}
