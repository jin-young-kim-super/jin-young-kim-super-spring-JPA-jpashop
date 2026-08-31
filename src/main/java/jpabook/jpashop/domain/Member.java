package jpabook.jpashop.domain;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseEntity{
    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String username;

    @Embedded
    private Period period;

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Embedded
    private Address homeAddress; // 집 주소

    /**
     * 엔티티 객체 내에 같은 임베디드 타입이 들어가면 에러 발생
     * -> Address내의 필드명이 겹치기 때문이다. 그래서 @AttributeOverids를 사용하여
     * Address 내의 필드명의 재정의하여 DB 쿼리문 작성 시에 문제가 발생하지 않도록 한다.
     * 그리고 당연한 이야기이겠지만, 임베디드 타입에 null이 들어 가면 DB 쿼리 작성 시 그 안의 필드값도 전부 null로 세팅된다.
     */

}








