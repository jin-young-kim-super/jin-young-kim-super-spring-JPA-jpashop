package jpabook.jpashop.domain;


import jakarta.persistence.*;

/**
 * Setter 사용은 디버깅을 어렵게 한다. 최대한 생성자에서 세팅을 끝내자.
 * -> 디버깅을 할 떄 setter 사용의경우 Member가 어느 코드에서 setter에 의해 값이 변경됐는지 일일이 찾아야 한다.
 * 반면 생성자를 통한 세팅의 경우, 생성자 한 곳만 보면 된다. 즉 유지보수성이 떨어 진다.
 */

@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")

    private String name;
    //@Column(name = "TEAM_ID")
    //private Long teamId; // 참조가 아닌 외래키 사용!(RDB 설계에 맞춤)

    // 연관 관계 매핑은 항상 DB입장에서 매핑을 해야 한다.
    @ManyToOne // DB 입장에서는 Member 테이블에 대해 TEAM 테이블은 1의 관계
    @JoinColumn(name="TEAM_ID") // DB에서는 연관 관계인 Member 테이블에서 TEAM 테이블의 어떤 키를 FK로 매핑할 지 정보가 필요
    private Team team;          // DB에서는 반드시 多 테이블에 FK를 둔다.

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

    public Team getTeam() {
        return team;
    }

    public void changeTeam(Team team) {

        this.team = team;
        /**
         * 영한이 실무 팁 : Team.getMembers().add(member)이 부분은 연관 관계 주인 객체에서 편의 메서드 작성을 해 놓는다
         * -> 인간은 반드시 Team.getMembers().add(member)를 까먹는다. 그 실수를 방지하게 위해 이렇게 편의 메서드를 만들어 놓는 걸 추천
         */
        team.getMembers().add(this);
    }
}


