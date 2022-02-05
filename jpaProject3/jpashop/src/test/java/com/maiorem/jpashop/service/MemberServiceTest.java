package com.maiorem.jpashop.service;

import com.maiorem.jpashop.domain.Member;
import com.maiorem.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    @Rollback(value = false)
    public void testJoin() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);

        //then
//        em.flush();
        assertEquals(member, memberRepository.findOne(saveId));

    }

    @Test
    public void testValidationDuplicateMember() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("lee");

        Member member2 = new Member();
        member2.setName("lee");

        //when
        memberService.join(member1);
        try {
            memberService.join(member2); // 중복회원 예외처리 : 예외가 발생해야 한다
        } catch (IllegalStateException e) {
            return;
        }
        //then
        fail("예외가 발생해야 한다!");

    }

}