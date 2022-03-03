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

//            //단방향 연관관계 매핑
//            //저장
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member2 memebr = new Member2();
//            memebr.setName("member1");
//            memebr.setTeamId(team.getId());
//            em.persist(memebr);

//            //검색
//            Member2 findMember = em.find(Member2.class, memebr.getId());
//
//            Long findTeamId = findMember.getTeamId();
//            Team findTeam = em.find(Team.class, findTeamId);


//            //단방향 연관관계 매핑2
//            //저장
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member2 memebr = new Member2();
//            memebr.setName("member1");
//            memebr.setTeam(team);
//            em.persist(memebr);
//
//            // 두 클래스 모두에 연관관계 매핑 메서드가 있으면 위험.
//            // 양방향 매핑시에는 무한루프 조심
////            team.addMember(memebr);
//
//            em.flush();
//            em.clear();
////
////            //단방향 검색
////            Member2 findMember = em.find(Member2.class, memebr.getId());
////            Team findTeam = findMember.getTeam();
////
////            System.out.println("findTeam = "+findTeam.getName());
//
//            //양방향 연관관계 매핑
//            Member2 findMember = em.find(Member2.class, memebr.getId());
//            List<Member2> members = findMember.getTeam().getMembers();
//
//            for (Member2 m: members) {
//                System.out.println("m = " +m.getName());
//            }

            //일대다 매핑
//            Member2 member2 = new Member2();
//            member2.setUsername("member3");
//
//            em.persist(member2);
//
//            Team team = new Team();
//            team.setName("teamC");
//            team.getMembers().add(member2);
//
//            em.persist(team);

            //inheritance
            Movie movie = new Movie();
            movie.setDirector("aaaa");
            movie.setActor("nb");
            movie.setName("밞");
            movie.setPrice(10000);

            em.persist(movie);

            em.flush();
            em.clear();

            //join search
            Movie findMovie = em.find(Movie.class, movie.getId());
            System.out.println("findMovie = " + findMovie);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        enf.close();
    }
}
