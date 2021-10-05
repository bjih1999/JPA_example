package me.jihyun.jpashop.repository;

import me.jihyun.jpashop.domain.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
}
