package me.jihyun.jpashop.repository;

import me.jihyun.jpashop.domain.Member;

import java.util.List;

public interface MemberRepositoryCustom {

    public Member findOne(Long id);

    public List<Member> findByUsername(String username);
}
