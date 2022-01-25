package com.maiorem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.beans.PersistenceDelegate;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory enf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = enf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // insert data
            // 비영속
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("Hello");
            //영속
//            em.persist(member);

            // update data
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJpa");

            // JPQL
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10) //페이징
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member.name = " + member.getName());
//            }

            //시퀀스 테스트
//            Member member1 = new Member();
//            member1.setUsername("A");
//
//            Member member2 = new Member();
//            member2.setUsername("B");
//
//            Member member3 = new Member();
//            member3.setUsername("C");
//
//            em.persist(member1); // 시퀀스 allocationSize 만큼 호출
//            em.persist(member2); // 메모리 호출
//            em.persist(member3); // 메모리 호출

            //단방향 연관관계 매핑
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member2 memebr = new Member2();
            memebr.setName("member1");
            memebr.setTeamId(team.getId());
            em.persist(memebr);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        enf.close();
    }
}
