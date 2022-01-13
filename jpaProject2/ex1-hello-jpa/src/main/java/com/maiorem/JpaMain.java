package com.maiorem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.beans.PersistenceDelegate;

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
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJpa");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        enf.close();
    }
}
