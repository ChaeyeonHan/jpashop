package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    // 원래는 orderItems의 갯수만큼 저장을 해줘야하는데, cascade속성을 사용하면 order만 저장하면 나머지가 자동으로 저장된다(persist를 전파한다)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // order 저장시 delivery도 함께 persist 된다(안적어주면 각각 해줘야함)
    private Delivery delivery;  // 배송 정보

    private LocalDateTime orderDate;  // 주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문 상태(ORDER, CANCEL)

    // 연관관계 메서드 //
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItems(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

}
