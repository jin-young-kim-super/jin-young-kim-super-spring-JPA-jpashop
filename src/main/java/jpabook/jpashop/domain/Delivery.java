package jpabook.jpashop.domain;

import jakarta.persistence.*;

@Entity
public class Delivery extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;

    private String city;
    private String street;
    private String zipcode;
    private DeliveryStatus status;

    // 모든 연관 관계는 지연 로딩
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

}
