package me.jihyun.jpashop.Controller;

import lombok.RequiredArgsConstructor;
import me.jihyun.jpashop.domain.Address;
import me.jihyun.jpashop.domain.Member;
import me.jihyun.jpashop.domain.UserGroup;
import me.jihyun.jpashop.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("members/new")
    public String creatForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());

        Member member = Member.of(memberForm.getName(), address, new UserGroup());

        memberService.join(member);
        return "redirect:/";
    }

    /*
    화면을 출력할 때도 Dto를 사용하는 게 좋음
    API의 경우는 엔티티를 반환하면 절대 안됨
        - API는 스펙이 변함
    */
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
