package me.jihyun.jpashop.repository;

import me.jihyun.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/*
스프링 데이터 JPA로 간단하게 생성 가능하지만
JPA 자체를 다루는 것이 중요하다!
-> JPA로 개발한 후 추 후 스프링 데이터 JPA로 전환하자!
 */
@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByUsername(String username) {
        return em.createQuery("select m from Member m where m.name=:username", Member.class)
                .setParameter("username", username)
                .getResultList();
    }

}
