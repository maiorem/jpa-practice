package com.maiorem.jpashop.service;

import com.maiorem.jpashop.domain.Member;
import com.maiorem.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    
    // @Autowired => 생성자가 하나면 자동으로 오토와이어 해줌
    //public MemberService(MemberRepository memberRepository) {
    //    this.memberRepository = memberRepository;
    //}
    // ======> RequiredArgsConstructor가 자동으로 만들어주는 생성자

    //회원 가입
    @Transactional //읽기전용이 먹히지 않음
    public Long join(Member member) {
        validateDuplicateMemebr(member); //중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    // 중복 시 예외처리
    private void validateDuplicateMemebr(Member member) {
        //Exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    //회원 전체 조회 //읽기전용
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 단건 조회 //읽기전용
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
