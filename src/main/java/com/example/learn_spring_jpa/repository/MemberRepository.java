package com.example.learn_spring_jpa.repository;

import com.example.learn_spring_jpa.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 스프링 빈으로 자동 등록
public class MemberRepository {
    @PersistenceContext // 스프링 컨테이너에서 제공하는 엔티티 매니저 주입
    EntityManager entityManager;

    public void save(Member member) { // 회원 저장(영속화)
        entityManager.persist(member);
    }

    public Member findOne(Long id) { // 회원 식별자로 조회
        return entityManager.find(Member.class, id);
    }
    
    public List<Member> findAll() { // 회원 목록 조회
        return entityManager.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    
    public List<Member> findByName(String name) { // 회원이름으로 조회
        return entityManager.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
