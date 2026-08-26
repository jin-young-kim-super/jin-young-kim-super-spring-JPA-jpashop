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

            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            /**
             * 3번의 persist를 해줘야 한다
             * -> 개발자는 부모 중심으로 개발하고 있으니 영속성 관리는 Parent 쪽에서 해줬으면 좋겠다..ㅠㅠ
             * 그래서 부모 쪽에 cascade=CascadeType.ALL을 설정하면 child1,2에 대해 persist안 해줘도 자동 영속 상태가 된다.
             */
            entityManager.persist(parent);
            entityManager.persist(child1);
            entityManager.persist(child2);


            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {

            entityManager.close();
        }
        entityManagerFactory.close();

    }
}
