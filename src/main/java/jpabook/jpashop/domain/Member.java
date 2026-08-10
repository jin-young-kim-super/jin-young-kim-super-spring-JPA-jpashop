package jpabook.jpashop.domain;


import jakarta.persistence.*;

/**
 * Setter 사용은 디버깅을 어렵게 한다. 최대한 생성자에서 세팅을 끝내자.
 * -> 디버깅을 할 떄 setter 사용의경우 Member가 어느 코드에서 setter에 의해 값이 변경됐는지 일일이 찾아야 한다.
 * 반면 생성자를 통한 세팅의 경우, 생성자 한 곳만 보면 된다. 즉 유지보수성이 떨어 진다.
 */

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="MEMBER_ID")
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
