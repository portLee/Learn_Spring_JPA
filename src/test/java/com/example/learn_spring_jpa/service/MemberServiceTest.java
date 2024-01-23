package com.example.learn_spring_jpa.service;

import com.example.learn_spring_jpa.domain.Member;
import com.example.learn_spring_jpa.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void join() throws Exception { // 회원가입 테스트
        // Given
        Member member = new Member();
        member.setName("Kim");

        // When
        Long saveId = memberService.join(member);

        // Then
        Assertions.assertEquals(member, memberRepository.findOne(saveId)); // 객체 비교
    }

    @Test
    public void validateDuplicateMember() throws Exception { // 중복 회원 예외 처리 테스트
        // Given
        Member member1 = new Member();
        member1.setName("Kim");

        Member member2 = new Member();
        member2.setName("Kim");
        
        // When
        memberService.join(member1);
        memberService.join(member2); // 예외발생

        // Then
        fail("예외가 발생해야 한다.");
    }
}