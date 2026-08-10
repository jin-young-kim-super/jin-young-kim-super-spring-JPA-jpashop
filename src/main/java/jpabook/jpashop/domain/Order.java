package jpabook.jpashop.domain;


import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * @Table(name="ORDERS")
 * -> DB마다 order로 예약어가 걸려 있는 경우가 있어서, 테이블 명이 order인 경우 테이블 생성이 잘 안 되는 경우가 있다.
 * 그래서 관례상 ORDERS로 많이 사용
 */

/***
 * 영한 왈 : 되도록이면 도메인 객체, 즉 애플리케이션 코드에 DB 제약 조건을 다 걸자
 * -> 그래야 개발자가 DB를 일일이 뒤지지 않고 도메인 객체만 보고 DB 제약 조건을 바로 파악하여 손 쉽게 개발이 가능
 */

@Entity
@Table(name="ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ORDER_ID")
    private Long id;

    @Column(name="MEMBER_ID") // JPA를 직접 사용하면 테이블명이 객체명 그대로 저장이 되는데, 스프링 부트를 사용하면
                              // 객체명을 스네이크 기법으로 변환하여 저장을 한다.
    private Long memberId;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStaus staus;

}
