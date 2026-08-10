package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Team;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin(); // 트랜잭션 시작

        try {

            Team team = new Team();
            team.setName("TEAM A");
            entityManager.persist(team);

            Member member = new Member();
            member.setName("손흥민");

//            // 연관 관계를 참조가 아닌, 외래키로 관계 매핑을 해서 INSERT하면 생기는 문제점
//            Long teamId = team.getId(); // team_id 조회를 위해서 getId()를 해줘야 한다 : 객체지향 스러운 것은 member.setTeam(team);
//            member.setTeamId(teamId); // 외래키를 애플리케이션 코드에서 직접 사용하고 있다.
//            entityManager.persist(member);
//
//
//            // 연관 관계를 참조가 아닌, 외래키로 관계 매핑을 해서 SELECT하면 생기는 문제점
//            // -> 객체의 자유로운 그래프 탐색이 안된다.
//            Member findMember = entityManager.find(Member.class, member.getId());
//            Long findTeamId = findMember.getTeamId();
//            entityManager.find(Team.class, findTeamId); // 객체 지향 스로운 것은 그냥 Team.getMember()로 찾는 것이다.


            // 연관 관계를 참조로 매핑해서 INSERT문 실행
            member.setTeam(team); // 객체 지향성을 유지
            entityManager.persist(member);
            Member findMember = entityManager.find(Member.class, member.getId());

            // 연관 관계를 참조로 매핑해서 SELECT문 실행
            Team findTeam = member.getTeam(); // 객제 지향성을 유지

            transaction.commit(); // 트랜잭션 종료
        } catch (Exception e) {
            transaction.rollback(); // 트랜잭션 종료
        } finally {
            // STEP 4. DB 종료
            entityManager.close();
        }
        entityManagerFactory.close();

    }
}
