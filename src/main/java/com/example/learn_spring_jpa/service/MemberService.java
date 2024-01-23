package com.example.learn_spring_jpa.service;


import com.example.learn_spring_jpa.domain.Member;
import com.example.learn_spring_jpa.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 스프링 빈으로 등록
@Transactional // 메소드 실행시 트랜잭션 실행 종료 시 커밋, 예외 발생 시 롤백
public class MemberService {
    @Autowired // MemberRepository 주입
    private MemberRepository memberRepository;
    
    // 회원가입
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }
    
    private void validateDuplicateMember(Member member) { // 중복 회원 검사
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) { // 중복 회원이 존재하는 경우
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    
    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 조회
    public Member findMember(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
