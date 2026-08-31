package jpabook.jpashop;

import jakarta.persistence.*;
import jpabook.jpashop.domain.*;

import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin(); // 트랜잭션 시작

        try {

            Address address = new Address("city", "streetCode", "zipcode");

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setHomeAddress(address); // 임베디드 타입
            entityManager.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setHomeAddress(address); // 임베디드 타입
            entityManager.persist(member2);

            member1.getHomeAddress().setCity("newCity"); // member1의 주소 뿐아니라 member2의 주소도 같이 변경돼 버린다.(즉, 의도는 member1에 대한 update쿼리문 1개만 실행되야 하는데, 실제로는 member1,2둘다에 대해
                                                         // update문이 각각 하나씩 실행된다.

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {

            entityManager.close();
        }
        entityManagerFactory.close();

    }
}
