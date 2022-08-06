package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "delivery")  // @OneToOne관계에서는 FK 어느쪽에 두던 상관X
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)  // ORDINAL : 숫자로 들어간다. 중간에 상태가 추가된다면 수정하기 매우 힘들기에 STRING으로 사용!
    private DeliveryStatus status; // 배송상태 -> READY, COMP
}
