package me.jihyun.jpashop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberReposritory extends JpaRepository<Member, Long> {

}
