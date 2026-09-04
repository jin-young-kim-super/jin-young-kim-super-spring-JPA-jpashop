package jpabook.jpashop;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jpabook.jpashop.domain.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin(); // 트랜잭션 시작

        try {
            // JPQL은 엔티티(객체)를 대상으로 쿼리문 작성
//            List<Member> resultList = entityManager.createQuery(
//                    "select m From Member m Where m.username like '%kim%'" //  select m : m이라는 [모든] 엔티티를 조회(순수 SQL에서는 SELECT *에 해당)
//                    , Member.class
//            ).getResultList();
//
//            for (Member member : resultList) {
//                System.out.println("member = " + member);
//            }

            // JPA Criteria(참고로, JAVA 표준에서 제공하는 기능)
            // -> 특징은 오로지 자바 언어로만 SQL 작성을 한다는 것이다.
            // 아래 2줄은 Criteria 사용 준비 자바 표준 코드이다.
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            // From 절 설정
            Root<Member> m = query.from(Member.class);

            // 쿼리 완성
            CriteriaQuery<Member> sql = query.select(m).where(cb.equal(m.get("username"), "kim"));

            // 쿼리 실행
            List<Member> resultList = entityManager.createQuery(sql).getResultList();

            for (Member member : resultList) {
                System.out.println("member = " + member);
            }


            entityManager.flush();
            entityManager.close();

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {

            entityManager.close();
        }
        entityManagerFactory.close();

    }
}
