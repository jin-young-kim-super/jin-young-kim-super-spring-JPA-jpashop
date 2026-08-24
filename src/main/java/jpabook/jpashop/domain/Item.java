package jpabook.jpashop.domain;

import jakarta.persistence.*;

/**
 * 비지니스 로직 상 ITem 객체만 따로 생성해서 사용할 일이 없다고 가정
 * -> 그래서 abstract로 개발자들의 실수를 방지
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn // 생략해도 사실 DTYPE 컬럼 생성됨
public abstract class Item extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
