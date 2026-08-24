package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Movie;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin(); // 트랜잭션 시작

        try {
            /**
             * JPA의 장점
             * -> 상속 관계를 InheritanceType.JOINED에서 InheritanceType.SINGLE_TABLE로 바꿔도 혹은 그 반대로 바꿔서 DB 설계가 변경이 돼도
             * 애플리케이션은 하나도 손 대지 않아도 된다. 만약 JPA를 사용하지 않고 처음에 순수 SQL로 JOINED 전략으로 구현하고 DB 설계를 SINGLE_TABLE로
             * 변경을 하게 되면 애플리케이션은 거기에 맞춰 수정을 많이 해야 한다. 만약 JPA를 사용하면 만약 JOINED 전략에서 성능이 안 나오면 DBA와 상담을 하여
             * InheritanceType.JOINED를 InheritanceType.SINGLE_TABLE로 변경하기만 하면 된다.
             */
            Movie movie = new Movie();
            movie.setDirector("aaa");
            movie.setActor("bbbb");
            movie.setPrice(10000);
            entityManager.persist(movie);

            entityManager.flush();
            entityManager.clear();

            Movie findMovie = entityManager.find(Movie.class, movie.getId());

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {

            entityManager.close();
        }
        entityManagerFactory.close();

    }
}
