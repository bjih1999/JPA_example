package me.jihyun.jpashop.repository;

import me.jihyun.jpashop.domain.Member;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom{

    public Member findOne(Long id);

    public List<Member> findByUsername(String username);
}
