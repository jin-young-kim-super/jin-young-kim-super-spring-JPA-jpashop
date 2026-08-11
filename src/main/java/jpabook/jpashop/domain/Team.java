package jpabook.jpashop.domain;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team") // mappedby : 연관 관계 매핑 애노테이션이 걸려 있는 객체(List<Member>)가 그 객체의 어느 객체와 매핑이 걸려 있는지!!
                                  // 이걸 통해 이제 Team 객체에서도 Member 객체를 조회할 수가 있다.
    private List<Member> members = new ArrayList<>(); // members.add() 시 NUll포인터 예외 방지를 위해 미리 초기화 해둠

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

    public List<Member> getMembers() {
        return members;
    }
}