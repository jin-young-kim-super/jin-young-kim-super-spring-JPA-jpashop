package jpabook.jpashop;

import jakarta.persistence.*;
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

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity","street","10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");

            member.getAddressHistory().add(new Address("old1","old1","old1"));
            member.getAddressHistory().add(new Address("old2","old2","old2"));

            // 값 타입 컬력센 저장 예제
            // entityManager는 엔티티 타입만을 관리하기 때문에 값 타입 컬렉션은 persist하면 안 된다.
            // 그리고 값 타입 컬렉션은 CasCadeType.ALL + OrphanRemoval = true의 기능을 가진다.
            // -> member(부모)의 라이프 사이클과 값 타입 컬렉션의 라이프사이클이 같다.
            entityManager.persist(member); // 2개의 값 타입 컬렉션 테이블에 대해 2개의 INSERT문 실행

            entityManager.flush();
            entityManager.close();

            // 값 타입 컬렉션 조회 예제
            // SELECT * FROM Member WHERE member_id = ?? 가 실행
            // -> 값 타입 컬렉션은 @..toMany와 같기 때문에 지연 로딩이 된다.
            Member findMember = entityManager.find(Member.class, member.getId());

            // 값 컬렉션 타입은 지연 로딩이므로 사용 시에 비로소 SELECT문 실행이 된다.
            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            List<Address> addressHistory = findMember.getAddressHistory();

            // 값 타입 컬렉션 변경 예제
            // ex) HomeAddress의 city를 "homeCity" -> "newCity"로 변경하는 예제
            //findMember.getHomeAddress().setCity("newCity"); // 잘못된 변경 예시 : 값 타입은 항상 immutable해야 한다
            findMember.setHomeAddress(new Address("newCity","--","--")); // 값 타입은 항상 불변 객체로 새로 삽입

            // "치킨" -> "한식"
            Set<String> findFavoriteFoods = findMember.getFavoriteFoods();
            findFavoriteFoods.remove("치킨"); // DELETE문 실행
            findFavoriteFoods.add("한식"); // INSERT문 실행

            // 참고로 자바의 List는 remove시, equals(), HashCode()를 오버라이딩 하여, 참조값 비교가 아닌 "필드값" 비교를 통해
            // 같은 것이 있을 때 그걸 삭제한다. 그래서 값 타입 컬렉션 사용 시, 반드시 equals(), HashCode()를 오버라이딩 해 놓아야 한다.
            List<Address> findMemberAddressHistory = findMember.getAddressHistory();
            findMemberAddressHistory.remove(new Address("old1","old1","old1"));
            findMemberAddressHistory.add(new Address("newCity","---","---"));

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {

            entityManager.close();
        }
        entityManagerFactory.close();

    }
}
