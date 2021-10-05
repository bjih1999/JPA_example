package me.jihyun.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserGroup {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupName;

    @OneToMany(mappedBy = "group")
    private List<Member> members = new ArrayList<>();

    public static UserGroup of(String groupName) {
        UserGroup group = new UserGroup();
        group.groupName = groupName;

        return group;
    }

    public void addMember(Member member) {
        members.add(member);
    }
}
