package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Team;

import java.util.List;

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
            member.setTeam(team);
            entityManager.persist(member);

            entityManager.flush();
            entityManager.clear();

            Member findMember = entityManager.find(Member.class, member.getId());

            // mappedBy로 인해 이제 Team 객체에서도 Member 객체를 조회할 수 있게 되었다.
            // -> 양방향 매핑이 된 것이다.
            Team findTeam = findMember.getTeam();
            List<Member> members = findTeam.getMembers();
            for (Member m : members) {
                System.out.println("member = " + m.getName());
            }


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
