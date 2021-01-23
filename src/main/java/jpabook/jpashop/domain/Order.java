package jpabook.jpashop.domain;

import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문시간

    private OrderStatus status; // 주문상태 ORDER, CANCEL


    public Long getId() {
        return this.id;
    }

    public Member getMember() {
        return this.member;
    }

    public List<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public Delivery getDelivery() {
        return this.delivery;
    }

    public LocalDateTime getOrderDate() {
        return this.orderDate;
    }

    public OrderStatus getStatus() {
        return this.status;
    }
}
