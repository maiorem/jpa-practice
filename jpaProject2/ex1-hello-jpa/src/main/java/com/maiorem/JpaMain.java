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
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("Hello");
//            em.persist(member);

            // update data
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJpa");

            // JPQL
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10) //페이징
                    .getResultList();
            
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        enf.close();
    }
}
