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

    /**
     * changeMember()에서 하지 않아도, Team 객체에서 이렇게 addMember() 편의 메서드를 만들어 된다.
     * -> 난 이게 개인적으로 좋다.왜냐하면 협업 시 JPA에 익숙하지 않은 개발자가 "어? 왜 Team 객체에는 Member 객체를 삽입하지 않지?"
     * 라고 혼동할 수 있기에, JPA 로직에 Team.addMEmber(member)를 명시해 두는 게 개인적으로 더 좋다.
     * (고로, 보통 팀에서 이 문제를 Member객체에서 편의 메서드를 작성할 거냐, Team 객체에서 편의 메서드를 작성할 거냐를 정한다고 한다)
     */
    public void addMember(Member member) {
        this.members.add(member);
    }
}