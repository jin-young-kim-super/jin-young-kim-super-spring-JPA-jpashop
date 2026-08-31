package jpabook.jpashop.domain;


import jakarta.persistence.Embeddable;

/**
 * 임베디드 타입은 반드시 [기본 생성자] 필요
 */
@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    /**
     * 임베디드 타입인 Address를 불변 객체로 만들기 위해서 setter를 없앰
     */

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }
}
